package vice.hexforged.client.renderers;

import vice.hexforged.client.renderers.helpers.KineticGeckoTileAndItem;
import vice.hexforged.features.HexExtractionUnit.renderer.HexExtractionUnitKineticRenderer;
import vice.hexforged.features.HexExtractionUnit.renderer.HexExtractionUnitModel;
import vice.hexforged.features.HexExtractionUnit.HexExtractionUnitTileEntity;

public class GeckoRenderers
{
    public static KineticGeckoTileAndItem<HexExtractionUnitTileEntity, ?, ?> HexExtractionUnit = new KineticGeckoTileAndItem<>(new HexExtractionUnitModel(), HexExtractionUnitKineticRenderer::new);


}


