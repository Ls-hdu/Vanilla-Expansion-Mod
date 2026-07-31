package VanillaExpansion.content;

import VanillaExpansion.expand.ui.InvertedPyramidTreeLayout;
import mindustry.content.TechTree.TechNode;
import mindustry.ui.layout.TreeLayout.TreeNode;
import static VanillaExpansion.content.VEBlocks.*;
import static VanillaExpansion.content.VEItems.*;
import static mindustry.content.TechTree.*;

public class VETechTree {

    public static void load() {
        VEPlanets.proxima.techTree = nodeRoot("proxima", rockCoreDrill, () -> {
            node(proximaJunction, () -> {
                node(fastSideOutputConveyor, () -> {
                    node(overflow, () -> {
                        node(invertoverflow, () -> {});
                    });
                    node(adaptItemBridge, () -> {});
                });
                node(proximaDuctRouter, () -> {
                    node(proximaSorter, () -> {
                        node(proximaInvertSorter, () -> {});
                    });
                });
            });
            node(sideOutputConduit, () -> {
                node(liquidOverflowGate, () -> {
                    node(liquidUnderflowGate, () -> {});
                });
                node(liquidSorter, () -> {});
                node(adaptLiquidBridge, () -> {});
            });
            node(powerPole, () -> {
                node(test16Dir, () -> {});
            });

            nodeProduce(iron, () -> {
                nodeProduce(manganese, () -> {
                    nodeProduce(gold, () -> {
                        nodeProduce(uranium, () -> {});
                    });
                });
            });
        });
    }

    public static void layoutAll() {
        TechNode root = VEPlanets.proxima.techTree;
        if (root == null) return;
        applyLayout(root);
    }

    public static void applyLayout(TechNode node) {
        LayoutNode root = new LayoutNode(node, null);
        new InvertedPyramidTreeLayout().layout(root);
    }

    static class LayoutNode extends TreeNode<LayoutNode> {
        final TechNode node;

        LayoutNode(TechNode node, LayoutNode parent) {
            this.node = node;
            this.parent = parent;
            this.width = this.height = 60f;
            children = new LayoutNode[node.children.size];
            for (int i = 0; i < children.length; i++) {
                children[i] = new LayoutNode(node.children.get(i), this);
            }
        }
    }
}
