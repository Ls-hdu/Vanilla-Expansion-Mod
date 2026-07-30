package VanillaExpansion.expand.world.block;

import arc.util.io.*;

/**
 * 16方向数值的纯数据容器。
 * 存储一个 0-15 的旋转值，提供序列化和常见查询。
 * 
 * 注意：新架构下 SixteenDirectionBlock 的 Building 已不再使用此类，
 * 而是将完整16向值直接通过 config/configured 机制传递（全量管线）。
 * 此类保留给需要独立存储16向数据的旧代码或特殊用途。
 */
public class SixteenDirectionData {

    public static final int DIRECTIONS = 16;
    public static final float DEG_PER_DIR = 360f / DIRECTIONS;

    protected int rotation = 0;

    public SixteenDirectionData() {}

    public SixteenDirectionData(int rotation) {
        this.rotation = normalize(rotation);
    }

    public int get()             { return rotation; }
    public void set(int rot)     { this.rotation = normalize(rot); }
    public float deg()           { return rotation * DEG_PER_DIR; }
    public float rad()           { return (float) Math.toRadians(deg()); }
    public boolean isCardinal()  { return rotation % 4 == 0; }
    public int toCardinal()      { return isCardinal() ? rotation / 4 : -1; }
    public void fromCardinal(int c) { rotation = normalize(c * 4); }
    public void rotate(int steps)   { rotation = normalize(rotation + steps); }
    public void flip()              { rotate(DIRECTIONS / 2); }

    private int normalize(int r) {
        r %= DIRECTIONS;
        if (r < 0) r += DIRECTIONS;
        return r;
    }

    public void write(Writes w) { w.s(rotation); }
    public void read(Reads r)   { rotation = r.s(); }

    @Override
    public boolean equals(Object o) {
        return this == o || (o instanceof SixteenDirectionData d && rotation == d.rotation);
    }

    @Override
    public int hashCode() { return rotation; }

    @Override
    public String toString() {
        return "SixteenDirectionData{rot=" + rotation + ", deg=" + deg() + "}";
    }
}
