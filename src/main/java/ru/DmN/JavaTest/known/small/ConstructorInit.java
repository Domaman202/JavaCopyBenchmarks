package ru.DmN.JavaTest.known.small;

public class ConstructorInit {
    public static SmallDataClass copy(SmallDataClass original) {
        return new SmallDataClass(original);
    }
}
