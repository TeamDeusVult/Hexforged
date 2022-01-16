package vice.hexforged;



import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.Registrate;
import com.simibubi.create.repack.registrate.builders.BlockBuilder;
import com.simibubi.create.repack.registrate.providers.ProviderType;
import com.simibubi.create.repack.registrate.util.NonNullLazyValue;
import com.simibubi.create.repack.registrate.util.entry.RegistryEntry;
import com.simibubi.create.repack.registrate.util.nullness.NonNullFunction;
import lombok.val;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Block;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import vice.hexforged.registry.autoreg.AutoRegistry;
import vice.hexforged.registry.simple.RegisteredBlocks;
import vice.hexforged.registry.simple.RegisteredItems;
import vice.hexforged.registry.util.CustomRecipeBuilder;
import vice.hexforged.registry.util.ModItemTab;
import vice.hexforged.registry.util.RecipeHelper;

import java.util.function.Function;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("hexforged")
public class Hexforged
{
    public static final String ModID = "hexforged";
    public static final Logger Log = LogManager.getLogger();

    //region Registrate
    private static final NonNullLazyValue<CreateRegistrate> REGISTRATE = new NonNullLazyValue<CreateRegistrate>(() -> {
        CreateRegistrate ret = CreateRegistrate.lazy(Hexforged.ModID).get()
                .itemGroup(() -> ModItemTab.tab);

        ret.addDataGenerator(ProviderType.LANG, prov -> {
            prov.add(ModItemTab.tab, "Hexforged");
        });
        return ret;
    });

    public static CreateRegistrate registrate() {
        return REGISTRATE.get();
    }

    public static CreateRegistrate newObject(String name) {
        return REGISTRATE.get().object(name);
    }

    public static RegistryEntry<Item> simpleItem(String name, Function<CustomRecipeBuilder, CustomRecipeBuilder> recipe) {
        return REGISTRATE.get().object(name).item(Item::new).recipe((gen, prov) -> {
            if (recipe == null)
                return;
            val helper = RecipeHelper.Shaped(prov, gen.get());
            recipe.apply(helper).Save();
        }).register();
    }

    public static RegistryEntry<Item> simpleItemShapeless(String name, Supplier<IItemProvider> recipe) {
        return REGISTRATE.get().object(name).item(Item::new).recipe((gen, prov) -> {
            val helper = ShapelessRecipeBuilder.shapeless(gen.get()).requires(recipe.get()).unlockedBy(recipe.toString(), InventoryChangeTrigger.Instance.hasItems(recipe.get()));
            helper.save(prov);
        }).register();
    }

    public static <T extends Block> BlockBuilder<T, CreateRegistrate> newBlock(String name, String lang, NonNullFunction<Block.Properties, T> factory) {
        return REGISTRATE.get().object(name).block(factory).lang(lang);
    }

    //endregion



    public Hexforged() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);

        GeckoLib.initialize();

        AutoRegistry.initClasses("vice.hexforged.features");

        RegisteredBlocks.init();
        RegisteredItems.init();
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }
}