package deerangle.magicmod.block.entity;

import deerangle.magicmod.block.BlockRegistry;
import deerangle.magicmod.network.PacketHandler;
import deerangle.magicmod.network.SpawnMagicMessage;
import deerangle.magicmod.recipe.AltarRitualRecipe;
import deerangle.magicmod.recipe.RecipeRegistry;
import deerangle.magicmod.sound.SoundRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class AltarTileEntity extends ItemStandTileEntity {

    private LazyOptional<ItemStackHandler> inventory = LazyOptional.of(() -> new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            AltarTileEntity.super.updateDisplayNear();
        }

        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }
    });

    private int ritualTimer;
    private AltarRitualRecipe currentRecipe;

    public AltarTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public AltarTileEntity() {
        super(TileEntityRegistry.ALTAR);
    }

    private ItemStack getPedestalStack(BlockPos pos) {
        return ((PedestalTileEntity) getWorld().getTileEntity(pos)).getStackToDisplay();
    }

    private void removePedestalStack(BlockPos pos) {
        ((PedestalTileEntity) getWorld().getTileEntity(pos)).setStackToDisplay(ItemStack.EMPTY);
    }

    public boolean activate() {
        Configuration config = this.getCurrentConfiguration();

        BlockPos pedestalPos1 = getPos().add(2, 0, 2);
        BlockPos pedestalPos2 = getPos().add(-2, 0, 2);
        BlockPos pedestalPos3 = getPos().add(-2, 0, -2);
        BlockPos pedestalPos4 = getPos().add(2, 0, -2);

        if (ritualTimer == 0 && config == Configuration.BASIC) {
            ItemStack stack1 = getPedestalStack(pedestalPos1);
            ItemStack stack2 = getPedestalStack(pedestalPos2);
            ItemStack stack3 = getPedestalStack(pedestalPos3);
            ItemStack stack4 = getPedestalStack(pedestalPos4);
            ItemStack center = this.getStackToDisplay();
            IInventory inv = new IInventory() {
                @Override
                public int getSizeInventory() {
                    return 5;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public ItemStack getStackInSlot(int index) {
                    switch (index) {
                        case 0:
                            return center;
                        case 1:
                            return stack1;
                        case 2:
                            return stack2;
                        case 3:
                            return stack3;
                        case 4:
                            return stack4;
                        default:
                            return ItemStack.EMPTY;
                    }
                }

                @Override
                public ItemStack decrStackSize(int index, int count) {
                    return null;
                }

                @Override
                public ItemStack removeStackFromSlot(int index) {
                    return null;
                }

                @Override
                public void setInventorySlotContents(int index, ItemStack stack) {

                }

                @Override
                public void markDirty() {

                }

                @Override
                public boolean isUsableByPlayer(PlayerEntity player) {
                    return false;
                }

                @Override
                public void clear() {

                }
            };
            Optional<AltarRitualRecipe> recipeOpt = this.getWorld().getRecipeManager()
                    .getRecipe(RecipeRegistry.ALTAR_RITUAL_RECIPE, inv, getWorld());
            if (recipeOpt.isPresent()) {
                AltarRitualRecipe recipe = recipeOpt.get();
                performRitual(recipe);
                return true;
            }
        }
        return false;
    }

    private void performRitual(AltarRitualRecipe recipe) {
        ritualTimer = 300;
        getWorld().playSound(null, getPos().getX() + 0.5, getPos().getY() + 0.5, getPos().getZ() + 0.5,
                SoundRegistry.RITUAL, SoundCategory.BLOCKS, 3.0F, 1.0F);
        this.currentRecipe = recipe;
    }

    private void finishRitual() {
        LightningBoltEntity entity = EntityType.LIGHTNING_BOLT.create(getWorld());
        entity.func_233576_c_(Vector3d.func_237489_a_(getPos()));
        getWorld().addEntity(entity);
        getWorld().playSound(null, getPos().getX() + 0.5, getPos().getY() + 0.5, getPos().getZ() + 0.5,
                SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 10.0F, 1.0F);
        this.setStackToDisplay(this.currentRecipe.getRecipeOutput());
        BlockPos pedestalPos1 = getPos().add(2, 0, 2);
        BlockPos pedestalPos2 = getPos().add(-2, 0, 2);
        BlockPos pedestalPos3 = getPos().add(-2, 0, -2);
        BlockPos pedestalPos4 = getPos().add(2, 0, -2);
        this.removePedestalStack(pedestalPos1);
        this.removePedestalStack(pedestalPos2);
        this.removePedestalStack(pedestalPos3);
        this.removePedestalStack(pedestalPos4);
        this.currentRecipe = null;
    }

    @Override
    public void tick() {
        BlockPos pedestalPos1 = getPos().add(2, 0, 2);
        BlockPos pedestalPos2 = getPos().add(-2, 0, 2);
        BlockPos pedestalPos3 = getPos().add(-2, 0, -2);
        BlockPos pedestalPos4 = getPos().add(2, 0, -2);
        Vector3d targetPos = Vector3d.func_237489_a_(getPos()).add(0, 0.4, 0);

        super.tick();
        if (this.ritualTimer > 0) {
            this.ritualTimer--;
            PacketDistributor.PacketTarget target = PacketDistributor.NEAR.with(PacketDistributor.TargetPoint
                    .p(getPos().getX(), getPos().getY(), getPos().getZ(), 64, getWorld().func_234923_W_()));
            PacketHandler.INSTANCE.send(target,
                    new SpawnMagicMessage(pedestalPos1.getX() + 0.5, pedestalPos1.getY() + 1, pedestalPos1.getZ() + 0.5,
                            0.0, 2.0, 0.0, targetPos.getX(), targetPos.getY(), targetPos.getZ()));
            PacketHandler.INSTANCE.send(target,
                    new SpawnMagicMessage(pedestalPos2.getX() + 0.5, pedestalPos2.getY() + 1, pedestalPos2.getZ() + 0.5,
                            0.0, 2.0, 0.0, targetPos.getX(), targetPos.getY(), targetPos.getZ()));
            PacketHandler.INSTANCE.send(target,
                    new SpawnMagicMessage(pedestalPos3.getX() + 0.5, pedestalPos3.getY() + 1, pedestalPos3.getZ() + 0.5,
                            0.0, 2.0, 0.0, targetPos.getX(), targetPos.getY(), targetPos.getZ()));
            PacketHandler.INSTANCE.send(target,
                    new SpawnMagicMessage(pedestalPos4.getX() + 0.5, pedestalPos4.getY() + 1, pedestalPos4.getZ() + 0.5,
                            0.0, 2.0, 0.0, targetPos.getX(), targetPos.getY(), targetPos.getZ()));
            if (this.ritualTimer == 0) {
                this.finishRitual();
            }
        }
    }

    private Configuration getCurrentConfiguration() {
        BlockPos pedestalPos1 = getPos().add(2, 0, 2);
        BlockPos pedestalPos2 = getPos().add(-2, 0, 2);
        BlockPos pedestalPos3 = getPos().add(-2, 0, -2);
        BlockPos pedestalPos4 = getPos().add(2, 0, -2);

        if (getWorld().getBlockState(pedestalPos1).getBlock() == BlockRegistry.PEDESTAL && getWorld()
                .getBlockState(pedestalPos2).getBlock() == BlockRegistry.PEDESTAL && getWorld()
                .getBlockState(pedestalPos3).getBlock() == BlockRegistry.PEDESTAL && getWorld()
                .getBlockState(pedestalPos4).getBlock() == BlockRegistry.PEDESTAL) {
            return Configuration.BASIC;
        } else {
            return Configuration.NONE;
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inventory.cast();
        }
        return super.getCapability(cap);
    }

    @Override
    public ItemStack getStackToDisplay() {
        IItemHandler itemHandler = this.inventory.orElse(null);
        return itemHandler.getStackInSlot(0);
    }

    @Override
    public void setStackToDisplay(ItemStack stack) {
        ItemStackHandler itemHandler = this.inventory.orElse(null);
        itemHandler.setStackInSlot(0, stack);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inventory", inventory.orElse(null).serializeNBT());
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        inventory.orElse(null).deserializeNBT(compound.getCompound("inventory"));
        super.read(state, compound);
    }

    private enum Configuration {
        BASIC, NONE;
    }
}
