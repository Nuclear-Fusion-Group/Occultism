package com.occultism.TileEntity;

import com.occultism.item.OIItems;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ManaExtractorTileEntity extends TileEntity implements ITickableTileEntity {

    private int mana = 0;
    private int progress;
    private int totalProgress = 100;

    private ItemStackHandler inputHandler = new ItemStackHandler(1) {
        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            //限定输入物品
            return stack.getItem() == OIItems.bucket.get();
        }
    };

    private ItemStackHandler outputHandler = new ItemStackHandler(1) {
        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            return false;
        }
    };

    private FluidHandlerItemStack fluidHandler = new FluidHandlerItemStack(OIItems.mana_bucket.get().getDefaultInstance(), 1) {
        @Override
        public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {
            tank = mana;
            return super.isFluidValid(tank, stack);
        }
    };

    private LazyOptional<ItemStackHandler> inputHandlerQuote = LazyOptional.of(() -> inputHandler);
    private LazyOptional<ItemStackHandler> outputHandlerQuote = LazyOptional.of(() -> outputHandler);
    private LazyOptional<FluidHandlerItemStack> fluidHandlerQuote = LazyOptional.of(() -> fluidHandler);

    public ManaExtractorTileEntity() {
        super(TileEntityTypeRegister.manaextractortileentity.get());
    }

    //加载数据
    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        this.mana = nbt.getInt("mana");

        inputHandlerQuote.ifPresent(handler -> handler.deserializeNBT(nbt.getCompound("input")));
        outputHandlerQuote.ifPresent(handler -> handler.deserializeNBT(nbt.getCompound("output")));
    }

    //保存数据
    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        inputHandlerQuote.ifPresent(itemStackHandler -> nbt.put("input", itemStackHandler.serializeNBT()));
        inputHandlerQuote.ifPresent(itemStackHandler -> nbt.put("output", itemStackHandler.serializeNBT()));

        nbt.putInt("mana", mana);
        return super.save(nbt);
    }

    @Override
    public void tick() {
        if (!super.level.isClientSide()) {
            if (progressCheck() | finishedCheck() | manaCheck()) {
                this.setChanged();
            }
        }
    }

    //魔力
    private boolean manaCheck() {
        if (mana <= 5000)
            mana++;
        return true;
    }

    //进行
    private boolean progressCheck() {
        ItemStack inputInSlot = inputHandler.getStackInSlot(0);
        if (inputInSlot.getItem() == Items.AIR) {
            progress = 0;
        } else if (inputInSlot.getCount() > 0
                && inputInSlot.getItem() != Items.AIR) {
            progress++;
        }
        return true;
    }

    //完成
    private boolean finishedCheck() {
        ItemStack inputInSlot = inputHandler.getStackInSlot(0);
        if (progress >= totalProgress
                && mana >= 1000) {
            progress = 0;
            mana = mana - 1000;
            outputHandler.setStackInSlot(0, OIItems.mana_bucket.get().getDefaultInstance());

            inputInSlot.shrink(1);

            return true;
        }
        return false;
    }

    @Nonnull
    @Override
    public CompoundNBT getUpdateTag() {
        return super.getUpdateTag();
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            switch (side) {
                case UP:
                    return inputHandlerQuote.cast();
                case DOWN:
                    return outputHandlerQuote.cast();
                default:
                    return fluidHandlerQuote.cast();
            }
        }
        return super.getCapability(cap, side);
    }
}
