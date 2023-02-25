package net.mcreator.kakan.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.kakan.item.CrownOfLilyOfTheValleyItem;
import net.mcreator.kakan.KakanModElements;

import java.util.Map;
import java.util.HashMap;

@KakanModElements.ModElement.Tag
public class LilyPoisonAttackProcedure extends KakanModElements.ModElement {
	public LilyPoisonAttackProcedure(KakanModElements instance) {
		super(instance, 45);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure LilyPoisonAttack!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				System.err.println("Failed to load dependency sourceentity for procedure LilyPoisonAttack!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if ((((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).inventory.armorInventory.get((int) 3) : ItemStack.EMPTY)
				.getItem() == new ItemStack(CrownOfLilyOfTheValleyItem.helmet, (int) (1)).getItem())) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.POISON, (int) 101, (int) 0, (true), (true)));
		}
	}

	@SubscribeEvent
	public void onPlayerCriticalHit(CriticalHitEvent event) {
		Entity entity = event.getTarget();
		PlayerEntity sourceentity = event.getPlayer();
		double i = sourceentity.getPosX();
		double j = sourceentity.getPosY();
		double k = sourceentity.getPosZ();
		World world = sourceentity.world;
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("sourceentity", sourceentity);
		dependencies.put("damagemodifier", event.getDamageModifier());
		dependencies.put("isvanillacritical", event.isVanillaCritical());
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
