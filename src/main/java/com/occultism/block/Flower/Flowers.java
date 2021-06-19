package com.occultism.block.Flower;

import com.occultism.block.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.common.IPlantable;

import java.util.Arrays;
import java.util.List;

/**
 * ����ο���AOA3
 */
public class Flowers extends Block implements IForgeShearable, IPlantable {
    protected final List<Material> growthMaterials;

    /**
     *
     * @param material ����
     * @param mapColour ��ͼ��ɫ
     * @param sound ����
     * @param lightLevel ����ǿ��
     * @param growthMaterials ��ֲ���跽��
     */
    public Flowers(Material material, MaterialColor mapColour, SoundType sound, int lightLevel, Material... growthMaterials) {
        super(new BlockUtil.CompactProperties(material, mapColour).stats(0, 0).sound(sound).light(lightLevel).noClip().get());

        this.growthMaterials = Arrays.asList(growthMaterials);
    }

    /**
     *
     * @param material ����
     * @param mapColour ��ͼ��ɫ
     * @param sound ����
     * @param growthMaterials ��ֲ���跽��
     */
    public Flowers(Material material, MaterialColor mapColour, SoundType sound, Material... growthMaterials) {
        this(material, mapColour, sound, 0, growthMaterials);
    }

    /**
     *
     * @param material ����
     * @param mapColour ��ͼ��ɫ
     * @param growthMaterials ��ֲ���跽��
     */
    public Flowers(Material material, MaterialColor mapColour, Material... growthMaterials) {
        this(material, mapColour, SoundType.GRASS, growthMaterials);
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
        //��ȡ�����
        BlockState targetState = world.getBlockState(pos.below());
        //�ж���ֲ���跽���Ƿ�Ϊ�պ��Ƿ��������� �����ж��Ƿ�Ϊʵ����Ⱦ
        return (growthMaterials.isEmpty() || growthMaterials.contains(targetState.getMaterial())) && targetState.isSolidRender(world, pos.below());
    }

    @Override
    public BlockState getPlant(IBlockReader world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);

        if (state.getBlock() != this)
            return defaultBlockState();

        return state;
    }
}
