package VanillaExpansion.content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.type.*;

/**
 * Proxima物品注册
 */
public class VEItems {
    public static Item iron, uranium, manganese, gold;
    
    public static final Seq<Item> proximaOreItems = new Seq<>();
    
    public static Item plutonium238BerylliumSource; // 钚238-铍中子源
    public static Item heu235UraniumFuel; // HEU-235铀燃料棒
    public static Item dgammaFuel;
    

    
    public static void load(){
        iron = new Item("iron", Color.valueOf("a8a8a8")){{
            hardness = 2;
            cost = 0.8f;
            databaseTag = "basic-item";
        }};
        
        uranium = new Item("uranium", Color.valueOf("7fff00")){{
            hardness = 5;
            cost = 1.5f;
            radioactivity = 1.2f;
            explosiveness = 0.3f;
            healthScaling = 0.15f;
            databaseTag = "basic-item";
        }};
        
        manganese = new Item("manganese", Color.valueOf("E35745FF")){{
            hardness = 4;
            cost = 1.3f;
            healthScaling = 0.7f;
            databaseTag = "basic-item";
        }};

        gold = new Item("gold", Color.valueOf("ffd37f")){{
            hardness = 2;
            cost = 1.0f;
            databaseTag = "basic-item";
        }};
        
        proximaOreItems.addAll(iron, uranium, manganese, gold);
        
        // 钚238-铍中子源 - 深蓝色带放射性
        plutonium238BerylliumSource = new RBMKRodItem("plutonium238-beryllium-source", new Color(0.2f, 0.3f, 0.8f)){{
            yield = 0.8f;
            heat = 15f;
            selfRate = 0.3f;
            diffusion = 0.8f;
            meltingPoint = 2500f;
            isNeutronSource = true;
            enrichment = 1f;
            cost = 5000;
            radioactivity = 5f;
            databaseTag = "processed-item";
        }};
        
        // HEU-235铀燃料棒 - 亮绿色
        heu235UraniumFuel = new RBMKRodItem("heu235-uranium-fuel", new Color(0.3f, 0.8f, 0.2f)){{
            yield = 1f;
            heat = 20f;
            selfRate = 0.05f;
            diffusion = 1f;
            meltingPoint = 2000f;
            isNeutronSource = false;
            enrichment = 0.95f;
            cost = 3000;
            radioactivity = 3f;
            databaseTag = "processed-item";
        }};
        // 迪伽马燃料棒
        dgammaFuel = new RBMKRodItem("dgamma-source", Color.valueOf("C70000FF")){{
            yield = 0.8f;
            heat = 15f;
            selfRate = 0.3f;
            diffusion = 0.8f;
            meltingPoint = 2500f;
            isNeutronSource = false;
            enrichment = 1f;
            cost = 5000;
            radioactivity = 5f;
            databaseTag = "processed-item";
        }};
    }
}
