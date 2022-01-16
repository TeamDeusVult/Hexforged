package vice.hexforged.registry.util;

import com.google.gson.JsonElement;
import com.simibubi.create.repack.registrate.Registrate;
import com.simibubi.create.repack.registrate.builders.BlockBuilder;
import com.simibubi.create.repack.registrate.builders.ItemBuilder;
import com.simibubi.create.repack.registrate.providers.ProviderType;
import com.simibubi.create.repack.registrate.providers.RegistrateItemModelProvider;
import com.simibubi.create.repack.registrate.util.nullness.NonNullBiConsumer;
import com.simibubi.create.repack.registrate.util.nullness.NonNullBiFunction;
import lombok.val;
import lombok.var;
import net.minecraft.block.Block;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;

import java.util.Optional;
import java.util.function.Function;

public class RegistrateExtensions
{
    public static <T extends BlockItem, P> ItemBuilder<T, P> shapedRecipe(
            ItemBuilder<T, P> builder,
            Function<CustomRecipeBuilder, CustomRecipeBuilder> recipe)
    {
        builder.recipe((gen, prov) -> {
            val helper = RecipeHelper.Shaped(prov, gen.get());
            recipe.apply(helper).Save();
        });

        return builder;
    }

    public static <T extends Block, P> BlockBuilder<T, P> itemWithRecipe(
            BlockBuilder<T, P> builder,
            Function<CustomRecipeBuilder, CustomRecipeBuilder> recipe)
    {
        var item = builder.item();

        item.recipe((gen, prov) -> {
            val helper = RecipeHelper.Shaped(prov, gen.get());
            recipe.apply(helper).Save();
        });

        return item.build();
    }

    public static <T extends Block, P> ItemBuilder<BlockItem, BlockBuilder<T, P>> geckoItem(
            BlockBuilder<T, P> builder,
            NonNullBiFunction<? super T, Item.Properties, ? extends BlockItem> factory)
    {
        val itemBuilder = builder
                .getOwner()
                .item(builder, builder.getName(), (p) -> (BlockItem)factory.apply(builder.getEntry(), p));

        itemBuilder.setData(ProviderType.LANG, NonNullBiConsumer.noop());

        itemBuilder.model((ctx, prov) -> {
            prov.getBuilder(ctx.getName())
                .parent(new ModelFile.UncheckedModelFile("builtin/entity"))
                .transforms()
                    .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
                        .rotation(75, 45, 0)
                        .scale(0.375f, 0.375f, 0.375f)
                        .translation(0, 2.5f, 0)
                        .end()
                    .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
                        .rotation(75, 45, 0)
                        .scale(0.375f, 0.375f, 0.375f)
                        .translation(0, 2.5f, 0)
                        .end()
                    .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
                        .rotation(0, 225, 0)
                        .scale(0.4f, 0.4f, 0.4f)
                        .end()
                    .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
                        .rotation(0, 115, 0)
                        .scale(0.4f, 0.4f, 0.4f)
                        .end()
                    .transform(ModelBuilder.Perspective.GROUND)
                        .translation(0, 3, 0)
                        .scale(0.25f, 0.25f, 0.25f)
                        .end()
                    .transform(ModelBuilder.Perspective.GUI)
                        .rotation(30, 137, 0)
                        .translation(0, -3.75f, 0)
                        .scale(0.625f, 0.625f, 0.625f)
                        .end()
                    .transform(ModelBuilder.Perspective.FIXED)
                        .translation(0, -1.5f, 0)
                        .scale(0.5f, 0.5f, 0.5f)
                        .end()
                    .end();
        });

        return itemBuilder;
    }


}
