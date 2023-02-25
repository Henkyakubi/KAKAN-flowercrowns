package net.mcreator.kakan.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.kakan.KakanModElements;

import java.util.Map;

@KakanModElements.ModElement.Tag
public class CrownOfCornflowerHelmetTickEventProcedure extends KakanModElements.ModElement {
	public CrownOfCornflowerHelmetTickEventProcedure(KakanModElements instance) {
		super(instance, 41);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure CrownOfCornflowerHelmetTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 101, (int) 1, (false), (false)));
	}
}
