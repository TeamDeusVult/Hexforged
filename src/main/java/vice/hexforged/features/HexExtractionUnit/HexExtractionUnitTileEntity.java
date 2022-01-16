package vice.hexforged.features.HexExtractionUnit;

import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.components.mixer.MixerInstance;
import com.simibubi.create.repack.registrate.util.entry.TileEntityEntry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import vice.hexforged.Hexforged;
import vice.hexforged.client.renderers.GeckoRenderers;
import vice.hexforged.features.HexExtractionUnit.renderer.HexExtractionUnitKineticInstance;

public class HexExtractionUnitTileEntity extends KineticTileEntity implements IAnimatable
{
    public static final TileEntityEntry<HexExtractionUnitTileEntity> Registration =
            Hexforged.registrate()
                    .tileEntity("hex_extraction_unit_tile", HexExtractionUnitTileEntity::new)
                    .instance(() -> HexExtractionUnitKineticInstance::new)
                    .renderer(() -> GeckoRenderers.HexExtractionUnit.TileRenderer::apply)
                    .validBlock(HexExtractionUnitBlock.Registration)
                    .register();


    private final AnimationFactory factory = new AnimationFactory(this);

    private <E extends TileEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().transitionLengthTicks = 0;
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hex_extraction_unit.new", true));
        return PlayState.CONTINUE;
    }

    public HexExtractionUnitTileEntity(TileEntityType type) {
        super(type);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}