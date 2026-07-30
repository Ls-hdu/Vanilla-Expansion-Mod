package VanillaExpansion;

import arc.Events;
import arc.util.Log;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.world.Block;

import static mindustry.Vars.content;

public class ContentOrderGuard {
    public static void init() {
        if (!Vars.headless) return;

        Events.on(EventType.ContentInitEvent.class, e -> {
            Log.info("&lc===== VE Server Content IDs =====");
            Log.info("&lcBlocks: @ total, @ mod", content.blocks().size, content.blocks().count(b -> b.minfo.mod != null));
            for (Block b : content.blocks()) {
                if (b.minfo.mod != null) {
                    Log.info("&ly[@] id=@ class=@",
                        b.name, b.id, b.getClass().getSimpleName());
                }
            }
            Log.info("&lc================================");
        });
    }
}
