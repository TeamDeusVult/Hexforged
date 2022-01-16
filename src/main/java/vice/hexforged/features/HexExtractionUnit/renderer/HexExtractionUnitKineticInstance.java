package vice.hexforged.features.HexExtractionUnit.renderer;

import com.jozufozu.flywheel.backend.instancing.IDynamicInstance;
import com.jozufozu.flywheel.backend.material.MaterialManager;
import com.jozufozu.flywheel.core.materials.OrientedData;
import com.simibubi.create.AllBlockPartials;
import com.simibubi.create.content.contraptions.base.RotatingData;
import com.simibubi.create.content.contraptions.base.ShaftlessCogInstance;
import com.simibubi.create.content.contraptions.components.mixer.MechanicalMixerTileEntity;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import net.minecraft.util.Direction;
import vice.hexforged.features.HexExtractionUnit.HexExtractionUnitTileEntity;

public class HexExtractionUnitKineticInstance extends ShaftlessCogInstance implements IDynamicInstance
{
    private final RotatingData mixerHead;
    private final OrientedData mixerPole;
    private final HexExtractionUnitTileEntity mixer;

    public HexExtractionUnitKineticInstance(MaterialManager<?> dispatcher, HexExtractionUnitTileEntity tile) {
        super(dispatcher, tile);
        this.mixer = tile;

        mixerHead = getRotatingMaterial().getModel(AllBlockPartials.MECHANICAL_MIXER_HEAD, blockState)
                .createInstance();

        mixerHead.setRotationAxis(Direction.Axis.Y);

        mixerPole = getOrientedMaterial()
                .getModel(AllBlockPartials.MECHANICAL_MIXER_POLE, blockState)
                .createInstance();


        float renderedHeadOffset = getRenderedHeadOffset();

        transformPole(renderedHeadOffset);
        transformHead(renderedHeadOffset);
    }

    @Override
    public void beginFrame() {

        float renderedHeadOffset = getRenderedHeadOffset();

        transformPole(renderedHeadOffset);
        transformHead(renderedHeadOffset);
    }

    private void transformHead(float renderedHeadOffset) {
        float speed = 0;//mixer.getRenderedHeadRotationSpeed(AnimationTickHolder.getPartialTicks());

        mixerHead.setPosition(getInstancePosition())
                .nudge(0, -renderedHeadOffset, 0)
                .setRotationalSpeed(speed * 2);
    }

    private void transformPole(float renderedHeadOffset) {
        mixerPole.setPosition(getInstancePosition())
                .nudge(0, -renderedHeadOffset, 0);
    }

    private float getRenderedHeadOffset() {
        return 0;//mixer.getRenderedHeadOffset(AnimationTickHolder.getPartialTicks());
    }

    @Override
    public void updateLight() {
        super.updateLight();

        relight(pos.below(), mixerHead);
        relight(pos, mixerPole);
    }

    @Override
    public void remove() {
        super.remove();
        mixerHead.delete();
        mixerPole.delete();
    }
}
