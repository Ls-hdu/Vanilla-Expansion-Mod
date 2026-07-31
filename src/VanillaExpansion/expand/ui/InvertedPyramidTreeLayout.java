package VanillaExpansion.expand.ui;

import mindustry.ui.layout.TreeLayout;
import mindustry.ui.layout.TreeLayout.TreeNode;

/**
 * 倒金字塔科技树布局算法。
 * 根节点位于底部，每个深度作为一级(分级)，层级自下而上逐层展开、逐级变宽，构成倒金字塔形状。
 * TM的不生效啊
 * 可直接在自定义研发界面(类似 AquaResearchDialog)的 treeLayout() 中调用：
 * {@code new InvertedPyramidTreeLayout().layout(rootTechTreeNode);}
 */
public class InvertedPyramidTreeLayout implements TreeLayout {
    public float gapBetweenLevels = 160f;
    public float gapBetweenNodes = 20f;
    public int maxDepth;

    private float unit;
    private float maxNodeWidth = 0f;

    @Override
    public void layout(TreeNode root) {
        computeLeaves(root);

        maxDepth = computeDepth(root, 0);
        unit = maxNodeWidth + gapBetweenNodes;

        float totalWidth = Math.max(root.leaves, 1) * unit;
        position(root, -totalWidth / 2f, totalWidth / 2f, 0);
    }

    int computeLeaves(TreeNode node) {
        if (node.isLeaf()) return node.leaves = 1;
        int sum = 0;
        for (TreeNode child : node.children) sum += computeLeaves(child);
        return node.leaves = Math.max(sum, 1);
    }

    int computeDepth(TreeNode node, int depth) {
        maxNodeWidth = Math.max(maxNodeWidth, node.width);
        int max = depth;
        for (TreeNode child : node.children) max = Math.max(max, computeDepth(child, depth + 1));
        return max;
    }

    void position(TreeNode node, float start, float end, int depth) {
        node.x = (start + end) / 2f;
        node.y = (maxDepth - depth) * gapBetweenLevels;

        if (node.isLeaf()) return;

        float totalWeight = 0f;
        for (TreeNode child : node.children) totalWeight += weight(child);

        float cursor = start;
        for (TreeNode child : node.children) {
            float slice = (end - start) * weight(child) / Math.max(totalWeight, 0.0001f);
            position(child, cursor, cursor + slice, depth + 1);
            cursor += slice;
        }
    }

    float weight(TreeNode node) {
        return node.leaves + 2f;
    }
}
