package vice.hexforged.registry.util;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import vice.hexforged.Hexforged;
import vice.hexforged.registry.simple.RegisteredItems;

import javax.annotation.Nonnull;

public class ModItemTab {

    public static class ModCreativeTab extends ItemGroup
    {

        public ModCreativeTab(String name) {
            super(name);
        }

        @Override
        public boolean hasSearchBar() {
            return false;
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(RegisteredItems.ArcaneIngot.get());
        }
    }

    @Nonnull
    public static final ItemGroup tab = new ModCreativeTab(Hexforged.ModID);

}