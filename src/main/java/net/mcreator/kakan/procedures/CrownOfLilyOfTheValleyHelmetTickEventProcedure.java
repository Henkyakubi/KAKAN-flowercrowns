package net.mcreator.kakan.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.kakan.KakanModElements;

import java.util.Map;
import java.util.Collection;

@KakanModElements.ModElement.Tag
public class CrownOfLilyOfTheValleyHelmetTickEventProcedure extends KakanModElements.ModElement {
	public CrownOfLilyOfTheValleyHelmetTickEventProcedure(KakanModElements instance) {
		super(instance, 45);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure CrownOfLilyOfTheValleyHelmetTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == Effects.POISON)
							return true;
					}
				}
				return false;
			}
		}.check(entity)) && ((new Object() {
			int check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == Effects.POISON)
							return effect.getAmplifier();
					}
				}
				return 0;
			}
		}.check(entity)) == 0))) {
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).removePotionEffect(Effects.POISON);
			}
		}
	}
}
