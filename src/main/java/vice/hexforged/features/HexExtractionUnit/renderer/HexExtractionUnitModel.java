package vice.hexforged.features.HexExtractionUnit.renderer;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import vice.hexforged.Hexforged;

public class HexExtractionUnitModel extends AnimatedGeoModel
{
    @Override
    public ResourceLocation getModelLocation(Object object)
    {
        return new ResourceLocation(Hexforged.ModID, "geo/hex_extraction_unit.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Object object)
    {
        return new ResourceLocation(Hexforged.ModID, "textures/block/hex_extraction_unit.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Object object)
    {
        return new ResourceLocation(Hexforged.ModID, "animations/hex_extraction_unit.animation.json");
    }
}