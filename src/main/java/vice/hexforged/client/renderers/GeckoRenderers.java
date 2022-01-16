package vice.hexforged.client.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;
import vice.hexforged.features.HexExtractionUnit.renderer.HexExtractionUnitModel;
import vice.hexforged.features.HexExtractionUnit.HexExtractionUnitTileEntity;

import java.util.function.Function;
import java.util.function.Supplier;

public class GeckoRenderers
{
    public static KineticGeckoTileAndItem<HexExtractionUnitTileEntity, ?, ?> HexExtractionUnit = new KineticGeckoTileAndItem<>(new HexExtractionUnitModel());


    public static class GeckoTileAndItem<T extends TileEntity & IAnimatable, I extends Item & IAnimatable, M extends AnimatedGeoModel>
    {
        public Function<TileEntityRendererDispatcher, GeoBlockRenderer<T>> TileRenderer;
        public Supplier<GeoItemRenderer<I>> ItemRenderer;

        public GeckoTileAndItem(M model)
        {
            ItemRenderer = () -> new GeoItemRenderer<I>(model) {};
            TileRenderer = (dispatch) -> new GeoBlockRenderer<T>(dispatch, model)
            {
                @Override
                public RenderType getRenderType(T animatable, float partialTicks, MatrixStack stack,
                                                IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
                                                ResourceLocation textureLocation)
                {
                    return RenderType.entityTranslucent(getTextureLocation(animatable));
                }
            };
        }
    }

    public static class KineticGeckoTileAndItem<T extends TileEntity & IAnimatable, I extends Item & IAnimatable, M extends AnimatedGeoModel>
    {
        public Function<TileEntityRendererDispatcher, KineticGeoModelTileEntityRenderer<T>> TileRenderer;
        public Supplier<GeoItemRenderer<I>> ItemRenderer;

        public KineticGeckoTileAndItem(M model)
        {
            ItemRenderer = () -> new GeoItemRenderer<I>(model) {};
            TileRenderer = (dispatch) -> new KineticGeoModelTileEntityRenderer<T>(dispatch, model)
            {
                @Override
                public RenderType getRenderType(T animatable, float partialTicks, MatrixStack stack,
                                                IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
                                                ResourceLocation textureLocation)
                {
                    return RenderType.entityTranslucent(getTextureLocation(animatable));
                }
            };
        }
    }
}


