package ru.DmN.JavaTest.known.small;

public class HandsInit {
    public static SmallDataClass copy(SmallDataClass original) {
        var copy = new SmallDataClass();
        copy.a = original.a;
        copy.b = original.b;
        copy.c = original.c;
        copy.d = original.d;
        return copy;
    }
}
