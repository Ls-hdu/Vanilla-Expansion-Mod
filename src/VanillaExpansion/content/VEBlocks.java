package VanillaExpansion.content;

import VanillaExpansion.expand.world.block.distribution.AdaptItemBridge;
import VanillaExpansion.expand.world.block.distribution.Junction;
import VanillaExpansion.expand.world.block.distribution.MechanicalArm;
import VanillaExpansion.expand.world.block.distribution.SideOutputConveyor;
import VanillaExpansion.expand.world.block.liquid.AdaptLiquidBridge;
import VanillaExpansion.expand.world.block.liquid.Pipe;
import VanillaExpansion.expand.world.block.optics.LaserEmitter;
import VanillaExpansion.expand.world.block.optics.LaserMirror;
import VanillaExpansion.expand.world.block.optics.LaserReceiver;
import VanillaExpansion.expand.world.block.power.*;
import VanillaExpansion.expand.world.block.production.RockCoreDrill;
import VanillaExpansion.expand.world.block.production.RotatableCrafter;
import arc.struct.*;
import arc.graphics.Color;
import mindustry.content.*;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.gen.Sounds;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.distribution.OverflowGate;
import mindustry.world.blocks.environment.*;
import mindustry.world.consumers.ConsumeCoolant;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.meta.BuildVisibility;
import VanillaExpansion.expand.world.block.*;

public class VEBlocks {
    public static Block oreIron, oreUranium, oreManganese, oreQuartz;
    public static final Seq<Block> proximaOres = new Seq<>();

    //i say 神TM的物流
    public static Block fastSideOutputConveyor;
    public static Block proximaJunction;
    public static Block adaptItemBridge;
    public static Block overflow;
    public static Block invertoverflow;

    //i say 神TM的流体
    public static Block pipe;
    public static Block adaptLiquidBridge;

    // 16方向测试
    public static Block test16Dir;



    public static void load(){
        oreIron = new OreBlock(VEItems.iron){{
            variants = 3;
        }};

        oreUranium = new OreBlock(VEItems.uranium){{
            variants = 3;
        }};

        oreManganese = new OreBlock(VEItems.manganese){{
            variants = 3;
        }};


        proximaOres.addAll(oreIron, oreUranium, oreManganese, oreQuartz);

        // 分类物品桥
        adaptItemBridge = new AdaptItemBridge("adapt-item-bridge"){{
            requirements(Category.distribution, ItemStack.with(
                Items.copper, 50,
                Items.lead, 30,
                Items.titanium, 20,
                Items.silicon, 15
            ));
            buildVisibility = BuildVisibility.shown;
            alwaysUnlocked = true;

            hasPower = false;
            range = 6;
            health = 300;

            placeableLiquid = true;
        }};

        // 分类流体桥
        adaptLiquidBridge = new AdaptLiquidBridge("adapt-liquid-bridge"){{
            requirements(Category.distribution, ItemStack.with(
                Items.copper, 60,
                Items.lead, 40,
                Items.titanium, 25,
                Items.silicon, 20
            ));
            buildVisibility = BuildVisibility.shown;
            alwaysUnlocked = true;

            hasPower = false;
            range = 6;
            health = 350;
        }};
        // 高速侧输出传送带
        fastSideOutputConveyor = new SideOutputConveyor("fast-side-output-conveyor"){{
            speed = 0.15f;
            displayedSpeed = 20f;
            requirements(Category.distribution, ItemStack.with(
                Items.copper, 20,
                Items.lead, 10,
                Items.titanium, 5
            ));
        }};
        // 万用交叉器
        proximaJunction = new Junction("proxima-junction"){{
            requirements(Category.distribution, ItemStack.with(
                Items.copper, 15,
                Items.lead, 10
            ));
        }};
        // 管道
        pipe = new Pipe("pipe"){{
            requirements(Category.liquid, ItemStack.with(
                Items.copper, 10,
                Items.lead, 5
            ));
            bridgeReplacement = adaptLiquidBridge;
        }};
        overflow = new OverflowGate("proxima-overflow-gate"){{
            requirements(Category.distribution, ItemStack.with(
                    VEItems.iron, 1
            ));
            health = 45;
            invert = false;
        }};
        invertoverflow = new OverflowGate("proxima-underflow-gate"){{
            requirements(Category.distribution, ItemStack.with(
                    VEItems.iron, 1
            ));
            health = 45;
            invert = true;
        }};

        // 16方向测试方块
        test16Dir = new SixteenDirectionBlock("test-16dir"){{
            requirements(Category.distribution, ItemStack.with(Items.copper, 1));
            buildVisibility = BuildVisibility.shown;
            alwaysUnlocked = true;
            size = 1;
            destructible = true;
            health = 200;
            instantBuild = true;
            quickRotate = false;
        }};
    }
}
