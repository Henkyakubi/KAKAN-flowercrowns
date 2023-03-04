
package net.mcreator.kakan.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.Entity;

import net.mcreator.kakan.itemgroup.KAKANItemGroup;
import net.mcreator.kakan.KakanModElements;

@KakanModElements.ModElement.Tag
public class CrownOfPoppyItem extends KakanModElements.ModElement {
	@ObjectHolder("kakan:crown_of_poppy_helmet")
	public static final Item helmet = null;
	@ObjectHolder("kakan:crown_of_poppy_chestplate")
	public static final Item body = null;
	@ObjectHolder("kakan:crown_of_poppy_leggings")
	public static final Item legs = null;
	@ObjectHolder("kakan:crown_of_poppy_boots")
	public static final Item boots = null;
	public CrownOfPoppyItem(KakanModElements instance) {
		super(instance, 2);
	}

	@Override
	public void initElements() {
		IArmorMaterial armormaterial = new IArmorMaterial() {
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 1;
			}

			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{0, 0, 0, 0}[slot.getIndex()];
			}

			public int getEnchantability() {
				return 0;
			}

			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sand.place"));
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.EMPTY;
			}

			@OnlyIn(Dist.CLIENT)
			public String getName() {
				return "crown_of_poppy";
			}

			public float getToughness() {
				return 0f;
			}
		};
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.HEAD, new Item.Properties().group(KAKANItemGroup.tab)) {
			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "kakan:textures/models/armor/temp-poppy03__layer_" + (slot == EquipmentSlotType.LEGS ? "2" : "1") + ".png";
			}
		}.setRegistryName("crown_of_poppy_helmet"));
	}
}
