package net.mcreator.kakan.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;

import net.mcreator.kakan.KakanModElements;

import java.util.Map;

@KakanModElements.ModElement.Tag
public class CrownOfSweetBerryHelmetTickEventProcedure extends KakanModElements.ModElement {
	public CrownOfSweetBerryHelmetTickEventProcedure(KakanModElements instance) {
		super(instance, 52);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				System.err.println("Failed to load dependency itemstack for procedure CrownOfSweetBerryHelmetTickEvent!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((!((EnchantmentHelper.getEnchantmentLevel(Enchantments.THORNS, (itemstack)) != 0)))) {
			((itemstack)).addEnchantment(Enchantments.THORNS, (int) 1);
		}
	}
}
