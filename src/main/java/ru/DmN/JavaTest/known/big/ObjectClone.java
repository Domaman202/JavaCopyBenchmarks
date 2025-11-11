package ru.DmN.JavaTest.known.big;

public class ObjectClone {
    public static BigDataClass copy(BigDataClass original) throws CloneNotSupportedException {
        return (BigDataClass) original.clone();
    }
}
