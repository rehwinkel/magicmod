package deerangle.magicmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class PedestalBlock extends Block {

    private static final VoxelShape SHAPE = VoxelShapes
            .or(Block.makeCuboidShape(2, 10, 2, 14, 11, 14), Block.makeCuboidShape(4, 11, 4, 12, 11, 12),
                    Block.makeCuboidShape(2, 11, 2, 4, 12, 14), Block.makeCuboidShape(12, 11, 2, 14, 12, 14),
                    Block.makeCuboidShape(4, 11, 2, 12, 12, 4), Block.makeCuboidShape(4, 11, 12, 12, 12, 14),
                    Block.makeCuboidShape(4, 11, 4, 6, 12, 5), Block.makeCuboidShape(4, 11, 11, 6, 12, 12),
                    Block.makeCuboidShape(10, 11, 4, 12, 12, 5), Block.makeCuboidShape(10, 11, 11, 12, 12, 12),
                    Block.makeCuboidShape(4, 11, 5, 5, 12, 6), Block.makeCuboidShape(4, 11, 10, 5, 12, 11),
                    Block.makeCuboidShape(11, 11, 5, 12, 12, 6), Block.makeCuboidShape(11, 11, 10, 12, 12, 11),
                    Block.makeCuboidShape(2, 12, 2, 4, 14, 4), Block.makeCuboidShape(2, 12, 12, 4, 14, 14),
                    Block.makeCuboidShape(12, 12, 2, 14, 14, 4), Block.makeCuboidShape(12, 12, 12, 14, 14, 14),
                    Block.makeCuboidShape(2, 14, 2, 3, 15, 3), Block.makeCuboidShape(2, 14, 13, 3, 15, 14),
                    Block.makeCuboidShape(13, 14, 2, 14, 15, 3), Block.makeCuboidShape(13, 14, 13, 14, 15, 14),
                    Block.makeCuboidShape(3, 9, 3, 13, 10, 13), Block.makeCuboidShape(4, 7, 4, 12, 9, 12),
                    Block.makeCuboidShape(5, 1, 5, 11, 7, 11), Block.makeCuboidShape(3, 0, 4, 13, 1, 12),
                    Block.makeCuboidShape(11, 1, 5, 12, 2, 11), Block.makeCuboidShape(4, 1, 5, 5, 2, 11),
                    Block.makeCuboidShape(5, 1, 11, 11, 2, 12), Block.makeCuboidShape(5, 1, 4, 11, 2, 5),
                    Block.makeCuboidShape(11, 2, 6, 12, 3, 10), Block.makeCuboidShape(4, 2, 6, 5, 3, 10),
                    Block.makeCuboidShape(6, 2, 11, 10, 3, 12), Block.makeCuboidShape(6, 2, 4, 10, 3, 5),
                    Block.makeCuboidShape(4, 0, 12, 12, 1, 13), Block.makeCuboidShape(4, 0, 3, 12, 1, 4));

    public PedestalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
