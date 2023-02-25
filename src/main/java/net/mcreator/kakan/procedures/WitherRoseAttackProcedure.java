package net.mcreator.kakan.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.kakan.item.CrownOfWitherRoseItem;
import net.mcreator.kakan.KakanModElements;

import java.util.Map;
import java.util.HashMap;

@KakanModElements.ModElement.Tag
public class WitherRoseAttackProcedure extends KakanModElements.ModElement {
	public WitherRoseAttackProcedure(KakanModElements instance) {
		super(instance, 51);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure WitherRoseAttack!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				System.err.println("Failed to load dependency sourceentity for procedure WitherRoseAttack!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure WitherRoseAttack!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).inventory.armorInventory.get((int) 3) : ItemStack.EMPTY)
				.getItem() == new ItemStack(CrownOfWitherRoseItem.helmet, (int) (1)).getItem())) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.WITHER, (int) 101, (int) 0, (true), (true)));
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.SQUID_INK, (entity.getPosX()), ((entity.getPosY()) + 1), (entity.getPosZ()),
						(int) 6, 0.2, 0.2, 0.2, 0.2);
			}
			if (sourceentity instanceof LivingEntity)
				((LivingEntity) sourceentity).addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, (int) 1, (int) 1, (false), (true)));
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
