package net.mcreator.kakan.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.kakan.item.CrownOfPeonyItem;
import net.mcreator.kakan.KakanModElements;

import java.util.Map;
import java.util.HashMap;

@KakanModElements.ModElement.Tag
public class HandyPeonyProcedure extends KakanModElements.ModElement {
	public HandyPeonyProcedure(KakanModElements instance) {
		super(instance, 66);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure HandyPeony!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure HandyPeony!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure HandyPeony!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure HandyPeony!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure HandyPeony!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((entity.isSneaking())) {
			if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
					.getItem() == new ItemStack(Blocks.PEONY, (int) (1)).getItem())
					&& (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
							.getItem() == new ItemStack(Blocks.PEONY, (int) (1)).getItem()))) {
				if (((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)).getCount()) == 2)
						&& (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY))
								.getCount()) == 2))) {
					if (entity instanceof LivingEntity) {
						ItemStack _setstack = new ItemStack(Blocks.AIR, (int) (1));
						_setstack.setCount((int) 1);
						((LivingEntity) entity).setHeldItem(Hand.OFF_HAND, _setstack);
						if (entity instanceof ServerPlayerEntity)
							((ServerPlayerEntity) entity).inventory.markDirty();
					}
					if (entity instanceof LivingEntity) {
						ItemStack _setstack = new ItemStack(CrownOfPeonyItem.helmet, (int) (1));
						_setstack.setCount((int) 1);
						((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
						if (entity instanceof ServerPlayerEntity)
							((ServerPlayerEntity) entity).inventory.markDirty();
					}
					if (!world.getWorld().isRemote) {
						world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
										.getValue(new ResourceLocation("block.composter.fill_success")),
								SoundCategory.NEUTRAL, (float) 0.3, (float) 1);
					} else {
						world.getWorld().playSound(x, y, z,
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
										.getValue(new ResourceLocation("block.composter.fill_success")),
								SoundCategory.NEUTRAL, (float) 0.3, (float) 1, false);
					}
					world.addParticle(ParticleTypes.NOTE, x, y, z, 0, 0.1, 0);
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
