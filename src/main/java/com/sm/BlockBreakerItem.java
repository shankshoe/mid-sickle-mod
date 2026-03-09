package com.sm;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBreakerItem extends ToolItem {

    private final Block targetBlock1;
    private final Block targetBlock2;

    public BlockBreakerItem(ToolMaterial material, Block targetBlock1, Block targetBlock2, Settings settings) {
        super(material, settings);
        this.targetBlock1 = targetBlock1;
        this.targetBlock2 = targetBlock2;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient() && (state.isOf(targetBlock1) || state.isOf(targetBlock2))) {

            destroyNearbyBlocks((ServerWorld) world, pos);

            stack.damage(1, miner, EquipmentSlot.MAINHAND);
        }

        return super.postMine(stack, world, state, pos, miner);
    }

    private void destroyNearbyBlocks(ServerWorld world, BlockPos centerPos) {

        int radius = 2;
        int y = 0;

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {

                BlockPos pos = centerPos.add(x, y, z);
                if (pos.equals(centerPos)) continue;

                BlockState state = world.getBlockState(pos);

                if (state.isOf(targetBlock1) || state.isOf(targetBlock2)) {
                    world.breakBlock(pos, true);
                }
            }
        }
    }
}