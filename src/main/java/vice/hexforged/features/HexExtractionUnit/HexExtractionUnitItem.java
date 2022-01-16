package vice.hexforged.features.HexExtractionUnit;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import vice.hexforged.registry.util.ModItemTab;

public class HexExtractionUnitItem extends BlockItem implements IAnimatable
{
    private static final String CONTROLLER_NAME = "popupController";
    public AnimationFactory factory = new AnimationFactory(this);
    private static final int ANIM_OPEN = 0;

    public HexExtractionUnitItem(Block pBlock, Properties properties) {
        super(pBlock, properties.tab(ModItemTab.tab));
    }

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        // Not setting an animation here as that's handled below
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController controller = new AnimationController(this, CONTROLLER_NAME, 20, this::predicate);
        data.addAnimationController(controller);
    }


    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }




}
