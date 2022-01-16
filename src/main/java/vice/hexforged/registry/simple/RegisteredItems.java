package vice.hexforged.registry.simple;

import com.simibubi.create.repack.registrate.util.entry.RegistryEntry;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import vice.hexforged.Hexforged;

public class RegisteredItems
{
    public static void init() { }

    public static RegistryEntry<Item> RawHexCrystal = Hexforged.simpleItem("raw_hex_crystal", null);

    public static RegistryEntry<Item> RefinedHexCrystal = Hexforged.simpleItemShapeless("refined_hex_crystal", () -> RawHexCrystal.get());

    public static RegistryEntry<Item> PureHexCrystal = Hexforged.simpleItemShapeless("pure_hex_crystal", () -> RefinedHexCrystal.get());

    public static RegistryEntry<Item> ArcaneIngot = Hexforged.simpleItem("arcane_ingot", (builder) -> builder
            .Row(Items.GOLD_INGOT, RawHexCrystal.get(), Items.GOLD_INGOT)
            .Row(RawHexCrystal.get(), null, RawHexCrystal.get())
            .Row(Items.GOLD_INGOT, RawHexCrystal.get(), Items.GOLD_INGOT)
            .UnlockedBy(RawHexCrystal.get())
    );

    public static RegistryEntry<Item> ArcanePlating = Hexforged.simpleItemShapeless("arcane_plating", () -> RawHexCrystal.get());
}
