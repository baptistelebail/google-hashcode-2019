package com.liveaction.google.hashcode2019.model;

public final class Slice {

    public final int r1;
    public final int c1;
    public final int r2;
    public final int c2;

    public Slice(int r1, int c1, int r2, int c2) {
        this.r1 = r1;
        this.c1 = c1;
        this.r2 = r2;
        this.c2 = c2;
    }

    public Slice(Position p1, Position p2) {
        this(p1.row, p1.column, p2.row, p2.column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Slice slice = (Slice) o;

        if (r1 != slice.r1) return false;
        if (c1 != slice.c1) return false;
        if (r2 != slice.r2) return false;
        return c2 == slice.c2;
    }

    @Override
    public int hashCode() {
        int result = r1;
        result = 31 * result + c1;
        result = 31 * result + r2;
        result = 31 * result + c2;
        return result;
    }

    @Override
    public String toString() {
        return "[("+r1+","+c1+"),("+r2 +"," + c2 + ")]";
//        final StringBuilder sb = new StringBuilder("Slice{");
//        sb.append("r1=").append(r1);
//        sb.append(", c1=").append(c1);
//        sb.append(", r2=").append(r2);
//        sb.append(", c2=").append(c2);
//        sb.append('}');
//        return sb.toString();
    }

    public long size() {
        return (r2 - r1) * (c2 - c1);
    }
}
