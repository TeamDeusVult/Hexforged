/*
 * Copyright (c) 2020.
 * Author: Bernie G. (Gecko)
 */

package vice.hexforged;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import vice.hexforged.features.HexExtractionUnit.HexExtractionUnitBlock;

@Mod.EventBusSubscriber(modid = Hexforged.ModID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientListener
{
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerRenderers(final FMLClientSetupEvent event) {
		//ClientRegistry.bindTileEntityRenderer(HexExtractionUnitTileEntity.Registration.get(), HexExtractionUnitTileRenderer::new);

		RenderTypeLookup.setRenderLayer(HexExtractionUnitBlock.Registration.get(), RenderType.cutout());
	}
}
