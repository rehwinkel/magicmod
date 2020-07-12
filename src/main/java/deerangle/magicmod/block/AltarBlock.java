package deerangle.magicmod.block;

import deerangle.magicmod.block.entity.AltarTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class AltarBlock extends Block {

    private static final VoxelShape SHAPE = VoxelShapes.or(Block.makeCuboidShape(0, 0, 0, 16, 6, 16), Block.makeCuboidShape(1, 6, 1, 15, 13, 15));

    public AltarBlock(Block.Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.isIn(newState.getBlock())) {
            IItemHandler inventory = worldIn.getTileEntity(pos)
                    .getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
            NonNullList<ItemStack> items = NonNullList.create();
            for (int i = 0; i < inventory.getSlots(); i++) {
                items.add(i, inventory.getStackInSlot(i));
            }
            InventoryHelper.dropItems(worldIn, pos, items);

            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            IItemHandler inventory = worldIn.getTileEntity(pos)
                    .getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
            if (player.isSneaking()) {
                if (player.addItemStackToInventory(inventory.extractItem(0, 64, true))) {
                    inventory.extractItem(0, 64, false);
                }
            } else {
                if (player.getHeldItem(handIn).isEmpty()) {
                    player.setHeldItem(handIn, inventory.extractItem(0, 64, false));
                } else {
                    player.setHeldItem(handIn, inventory.insertItem(0, player.getHeldItem(handIn), false));
                }
            }
            return ActionResultType.CONSUME;
        }
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new AltarTileEntity();
    }
}
