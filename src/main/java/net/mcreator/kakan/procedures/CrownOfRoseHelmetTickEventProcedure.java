package net.mcreator.kakan.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;

import net.mcreator.kakan.KakanModElements;

import java.util.function.Function;
import java.util.Map;
import java.util.Comparator;

@KakanModElements.ModElement.Tag
public class CrownOfRoseHelmetTickEventProcedure extends KakanModElements.ModElement {
	public CrownOfRoseHelmetTickEventProcedure(KakanModElements instance) {
		super(instance, 60);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure CrownOfRoseHelmetTickEvent!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure CrownOfRoseHelmetTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((Entity) world
				.getEntitiesWithinAABB(MonsterEntity.class,
						new AxisAlignedBB((entity.getPosX()) - (20 / 2d), (entity.getPosY()) - (20 / 2d), (entity.getPosZ()) - (20 / 2d),
								(entity.getPosX()) + (20 / 2d), (entity.getPosY()) + (20 / 2d), (entity.getPosZ()) + (20 / 2d)),
						null)
				.stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
					}
				}.compareDistOf((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()))).findFirst().orElse(null)) != null)) {
			if ((!(((Entity) world
					.getEntitiesWithinAABB(MonsterEntity.class,
							new AxisAlignedBB((entity.getPosX()) - (20 / 2d), (entity.getPosY()) - (20 / 2d), (entity.getPosZ()) - (20 / 2d),
									(entity.getPosX()) + (20 / 2d), (entity.getPosY()) + (20 / 2d), (entity.getPosZ()) + (20 / 2d)),
							null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()))).findFirst().orElse(
							null)) instanceof LivingEntity
									? (((LivingEntity) ((Entity) world
											.getEntitiesWithinAABB(MonsterEntity.class,
													new AxisAlignedBB((entity.getPosX()) - (20 / 2d), (entity.getPosY()) - (20 / 2d),
															(entity.getPosZ()) - (20 / 2d), (entity.getPosX()) + (20 / 2d),
															(entity.getPosY()) + (20 / 2d), (entity.getPosZ()) + (20 / 2d)),
													null)
											.stream().sorted(new Object() {
												Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
													return Comparator
															.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
												}
											}.compareDistOf((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()))).findFirst().orElse(null)))
													.getCreatureAttribute() == CreatureAttribute.UNDEAD)
									: false))) {
				((Entity) world
						.getEntitiesWithinAABB(MonsterEntity.class,
								new AxisAlignedBB((entity.getPosX()) - (20 / 2d), (entity.getPosY()) - (20 / 2d), (entity.getPosZ()) - (20 / 2d),
										(entity.getPosX()) + (20 / 2d), (entity.getPosY()) + (20 / 2d), (entity.getPosZ()) + (20 / 2d)),
								null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()))).findFirst().orElse(null)).setFire((int) 2);
			}
		}
	}
}
