package ru.DmN.JavaTest.known.small;

public final class SmallDataClass implements Cloneable {
    int a;
    int b;
    int c;
    int d;

    public SmallDataClass() {
    }

    public SmallDataClass(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public SmallDataClass(SmallDataClass other) {
        this.a = other.a;
        this.b = other.b;
        this.c = other.c;
        this.d = other.d;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SmallDataClass data && (obj == this || this.a == data.a && this.b == data.b && this.c == data.c && this.d == data.d);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
