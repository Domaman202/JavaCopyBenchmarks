package ru.DmN.JavaTest.known.small;

import sun.misc.Unsafe;

public class SunUnsafeAccess {
    private static final Unsafe UNSAFE = getUnsafe();
    private static final long FIELD_A = fieldOffset("a");
    private static final long FIELD_B = fieldOffset("b");
    private static final long FIELD_C = fieldOffset("c");
    private static final long FIELD_D = fieldOffset("d");

    public static SmallDataClass copy(SmallDataClass original) throws Throwable {
        var copy = (SmallDataClass) UNSAFE.allocateInstance(SmallDataClass.class);
        UNSAFE.putInt(copy, FIELD_A, UNSAFE.getInt(original, FIELD_A));
        UNSAFE.putInt(copy, FIELD_B, UNSAFE.getInt(original, FIELD_B));
        UNSAFE.putInt(copy, FIELD_C, UNSAFE.getInt(original, FIELD_C));
        UNSAFE.putInt(copy, FIELD_D, UNSAFE.getInt(original, FIELD_D));
        return copy;
    }

    private static Unsafe getUnsafe() {
        try {
            var unsafe = Unsafe.class.getDeclaredField("theUnsafe");
            unsafe.setAccessible(true);
            return (Unsafe) unsafe.get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private static long fieldOffset(String name) {
        try {
            var field = SmallDataClass.class.getDeclaredField(name);
            field.setAccessible(true);
            return UNSAFE.objectFieldOffset(field);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
