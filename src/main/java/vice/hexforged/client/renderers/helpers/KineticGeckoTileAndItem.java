package vice.hexforged.client.renderers.helpers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;
import vice.hexforged.client.renderers.KineticGeoModelTileEntityRenderer;

import java.util.function.Function;
import java.util.function.Supplier;

public class KineticGeckoTileAndItem<T extends KineticTileEntity & IAnimatable, I extends Item & IAnimatable, M extends AnimatedGeoModel>
{
    public Function<TileEntityRendererDispatcher, KineticGeoModelTileEntityRenderer<T>> TileRenderer;
    public Supplier<GeoItemRenderer<I>> ItemRenderer;

    public KineticGeckoTileAndItem(M model)
    {
        ItemRenderer = () -> new GeoItemRenderer<I>(model)
        {
        };
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

    public KineticGeckoTileAndItem(M model, Function<TileEntityRendererDispatcher, KineticGeoModelTileEntityRenderer<T>> tileRenderer)
    {
        ItemRenderer = () -> new GeoItemRenderer<I>(model)
        {
        };
        TileRenderer = tileRenderer;
    }

    public KineticGeckoTileAndItem(Supplier<GeoItemRenderer<I>> itemRenderer, Function<TileEntityRendererDispatcher, KineticGeoModelTileEntityRenderer<T>> tileRenderer)
    {
        ItemRenderer = itemRenderer;
        TileRenderer = tileRenderer;
    }
}
