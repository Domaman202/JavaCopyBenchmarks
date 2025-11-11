package ru.DmN.JavaTest.known.small;

import sun.misc.Unsafe;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;

public class IntUnsafeBlockCopy {
    private static final Object UNSAFE = getUnsafe();
    private static final MethodHandle ALLOCATE;
    private static final MethodHandle MEM_COPY;
    private static final long FIRST;

    public static SmallDataClass copy(SmallDataClass original) throws Throwable {
        var copy = (SmallDataClass) ALLOCATE.invoke(UNSAFE, SmallDataClass.class);
        MEM_COPY.invoke(original, copy);
        return copy;
    }

    static {
        try {
            var field = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            forceAccess(field);
            var lookup = (MethodHandles.Lookup) field.get(null);
            ALLOCATE = lookup.unreflect(Class.forName("jdk.internal.misc.Unsafe").getDeclaredMethod("allocateInstance", Class.class));
            var offset = Class.forName("jdk.internal.misc.Unsafe").getDeclaredMethod("objectFieldOffset", Class.class, String.class);
            forceAccess(offset);
            FIRST = (long) offset.invoke(UNSAFE, SmallDataClass.class, "a");
            MEM_COPY = MethodHandles.insertArguments(MethodHandles.insertArguments(MethodHandles.insertArguments(lookup.unreflect(Class.forName("jdk.internal.misc.Unsafe").getDeclaredMethod("copyMemory0", Object.class, long.class, Object.class, long.class, long.class)).bindTo(UNSAFE), 1, FIRST), 2, FIRST), 2, 16);
        } catch (NoSuchMethodException | IllegalAccessException | ClassNotFoundException | NoSuchFieldException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object getUnsafe() {
        try {
            var field = Class.forName("jdk.internal.misc.Unsafe").getDeclaredField("theUnsafe");
            forceAccess(field);
            return field.get(null);
        } catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    // Костыль, но пока не знаю как заменить.
    private static void forceAccess(AccessibleObject field) {
        try {
            var unsafe = Unsafe.class.getDeclaredField("theUnsafe");
            unsafe.setAccessible(true);
            ((Unsafe) unsafe.get(null)).putBoolean(field, 12, true);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
