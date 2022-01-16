package vice.hexforged.features.HexExtractionUnit;

import com.simibubi.create.content.contraptions.base.DirectionalKineticBlock;
import com.simibubi.create.repack.registrate.util.entry.BlockEntry;
import lombok.experimental.ExtensionMethod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;
import software.bernie.geckolib3.core.IAnimatable;
import vice.hexforged.Hexforged;
import vice.hexforged.client.renderers.GeckoRenderers;
import vice.hexforged.registry.simple.RegisteredItems;
import vice.hexforged.registry.util.RecipeHelper;
import vice.hexforged.registry.util.RegistrateExtensions;

import javax.annotation.Nullable;

@ExtensionMethod({ RegistrateExtensions.class})
public class HexExtractionUnitBlock extends DirectionalKineticBlock
{
    public static final BlockEntry<HexExtractionUnitBlock> Registration =
            Hexforged.newBlock("hex_extraction_unit", "Hex Extraction Unit", HexExtractionUnitBlock::new)
                    .geckoItem(HexExtractionUnitItem::new)
                        .recipe((gen, prov) -> {
                            RecipeHelper.Shaped(prov, gen.get())
                                    .Row(Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT)
                                    .Row(Items.OAK_PLANKS, RegisteredItems.RawHexCrystal.get(), Items.OAK_PLANKS)
                                    .Row(Items.COBBLESTONE, Items.COBBLESTONE, Items.COBBLESTONE)
                                    .UnlockedBy(Items.DIRT);
                        })
                        .properties((props) -> new Item.Properties().setISTER(() -> GeckoRenderers.HexExtractionUnit.ItemRenderer::get))
                    .build()
                    .properties((props) -> Properties.of(Material.WOOD).noOcclusion())
                    .register();


    public HexExtractionUnitBlock(Properties props) {
        super(props);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {  return HexExtractionUnitTileEntity.Registration.create(); }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState blockState)
    {
        return Direction.Axis.X;
    }
}