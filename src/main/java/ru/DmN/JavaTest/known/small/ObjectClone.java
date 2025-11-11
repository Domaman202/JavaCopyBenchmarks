package ru.DmN.JavaTest.known.small;

public class ObjectClone {
    public static SmallDataClass copy(SmallDataClass original) throws CloneNotSupportedException {
        return (SmallDataClass) original.clone();
    }
}
