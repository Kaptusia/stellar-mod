package com.kaptusia.stellar.common.block.entity;

import com.kaptusia.stellar.common.block.ModBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class HookBlockEntity extends BlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public HookBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.HOOK_BLOCK_ENTITY_BLOCK_ENTITY_TYPE, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    public void addItem(Entity user, ItemStack stack) {
        this.inventory.set(0, stack.split(1));
        this.world.emitGameEvent(GameEvent.BLOCK_CHANGE, this.getPos(), GameEvent.Emitter.of(user, this.getCachedState()));
        this.updateListeners();
    }

    private void updateListeners() {
        this.markDirty();
        this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), Block.NOTIFY_ALL);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory.clear();
        Inventories.readNbt(nbt, this.inventory);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory, true);
    }

    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbtCompound = new NbtCompound();
        Inventories.writeNbt(nbtCompound, this.inventory, true);
        return nbtCompound;
    }

    public ItemStack getRenderStack() {
        return getStack(0).isEmpty() ? new ItemStack(Items.AIR) : this.getStack(0);
    }
}
