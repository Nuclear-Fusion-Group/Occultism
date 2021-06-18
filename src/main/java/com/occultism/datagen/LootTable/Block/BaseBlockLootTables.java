package com.occultism.datagen.LootTable.Block;

import com.occultism.api.IHasTileEntity;
import com.occultism.api.NBTConstants;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.functions.CopyNbt;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Set;

//�ú����ο���mek��ʵ��
public abstract class BaseBlockLootTables extends BlockLootTables {

    private final Set<Block> knownBlocks = new ObjectOpenHashSet<>();
    private final Set<Block> toSkip = new ObjectOpenHashSet<>();

    @Override
    protected abstract void addTables();

    /**
     * ���ע��ķ���
     *
     * @param block
     * @param table
     */
    @Override
    protected void add(@Nonnull Block block, @Nonnull LootTable.Builder table) {
        super.add(block, table);
        knownBlocks.add(block);
    }

    /**
     * ��ȡע��ķ���
     *
     * @return
     */
    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return knownBlocks;
    }

    /**
     * �����������
     *
     * @param blocks
     */
    protected void skip(Block... blocks) {
        Collections.addAll(toSkip, blocks);
    }

    /**
     * �ж��Ƿ�Ϊ��ע�����Եķ���
     *
     * @param block
     * @return
     */
    protected boolean skipBlock(Block block) {
        return knownBlocks.contains(block) || toSkip.contains(block);
    }

    /**
     * ����ע�᷽��ս��Ʒ��
     *
     * @param blocks
     */
    protected void dropSelfWithContents(List<Block> blocks) {
        for (Block block1 : blocks) {
            //��ȡ����
            Block block = block1.getBlock();
            //�ж��Ƿ�Ϊ��ע�᷽��
            if (skipBlock(block)) {
                continue;
            }
            //��ȡĬ��nbt����
            CopyNbt.Builder nbtBuilder = CopyNbt.copyData(CopyNbt.Source.BLOCK_ENTITY);

            boolean hasData = false;
            @Nullable
            TileEntity tile = null;
            //��ȡTileEntity
            if (block instanceof IHasTileEntity) {
                tile = ((IHasTileEntity) block).getTileType().create();
            }
            if (tile != null) {
                if (!(tile instanceof IItemHandler) || ((IItemHandler) tile).getSlots() > 0) {
                    nbtBuilder.copy(NBTConstants.ITEMS, NBTConstants.OI_DATA + "." + NBTConstants.ITEMS);
                    hasData = true;
                }
                if (!(tile instanceof IFluidHandlerItem)) {
                    nbtBuilder.copy(NBTConstants.MANA, NBTConstants.OI_DATA + "." + NBTConstants.MANA);
                    hasData = true;
                }
            }
            //�ж��Ƿ������� ���û�о�����Ĭ��ս��Ʒ��
            if (!hasData) {
                dropSelf(block);
            } else {
                add(block, LootTable.lootTable().withPool(applyExplosionCondition(block, LootPool.lootPool()
                        .name("main")
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(block).apply(nbtBuilder))
                )));
            }
        }
    }
}
