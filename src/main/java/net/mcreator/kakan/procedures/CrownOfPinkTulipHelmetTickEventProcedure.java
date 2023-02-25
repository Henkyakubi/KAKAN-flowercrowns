package net.mcreator.kakan.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.kakan.item.CrownOfPinkTulipItem;
import net.mcreator.kakan.KakanModElements;

import java.util.Map;
import java.util.HashMap;

@KakanModElements.ModElement.Tag
public class CrownOfPinkTulipHelmetTickEventProcedure extends KakanModElements.ModElement {
	public CrownOfPinkTulipHelmetTickEventProcedure(KakanModElements instance) {
		super(instance, 33);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure CrownOfPinkTulipHelmetTickEvent!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure CrownOfPinkTulipHelmetTickEvent!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure CrownOfPinkTulipHelmetTickEvent!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure CrownOfPinkTulipHelmetTickEvent!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure CrownOfPinkTulipHelmetTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.armorInventory.get((int) 3) : ItemStack.EMPTY)
				.getItem() == new ItemStack(CrownOfPinkTulipItem.helmet, (int) (1)).getItem())) {
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.PINK_TULIP.getDefaultState().getBlock())) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.ABSORPTION, (int) 6001, (int) 1, (false), (false)));
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z), (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
							.getValue(new ResourceLocation("block.enchantment_table.use")), SoundCategory.NEUTRAL, (float) 0.3, (float) 1);
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("block.enchantment_table.use")),
							SoundCategory.NEUTRAL, (float) 0.3, (float) 1, false);
				}
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.FIREWORK, x, (y + 0.5), z, (int) 12, 0.1, 0.1, 0.1, 0);
				}
			}
		}
	}

	@SubscribeEvent
	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		PlayerEntity entity = event.getPlayer();
		if (event.getHand() != entity.getActiveHand())
			return;
		int i = event.getPos().getX();
		int j = event.getPos().getY();
		int k = event.getPos().getZ();
		World world = event.getWorld();
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
