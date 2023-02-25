package net.mcreator.kakan.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.kakan.KakanModElements;

import java.util.Map;

@KakanModElements.ModElement.Tag
public class CrownOfRedTulipHelmetTickEventProcedure extends KakanModElements.ModElement {
	public CrownOfRedTulipHelmetTickEventProcedure(KakanModElements instance) {
		super(instance, 21);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure CrownOfRedTulipHelmetTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, (int) 101, (int) 0, (false), (false)));
	}
}
