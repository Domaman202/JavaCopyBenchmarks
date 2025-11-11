package ru.DmN.JavaTest.known.small;

import sun.misc.Unsafe;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;

public class IntUnsafeAccess {
    private static final Object UNSAFE = getUnsafe();
    private static final MethodHandle ALLOCATE;
    private static final MethodHandle GET_INT;
    private static final MethodHandle PUT_INT;
    private static final long FIELD_A = fieldOffset("a");
    private static final long FIELD_B = fieldOffset("b");
    private static final long FIELD_C = fieldOffset("c");
    private static final long FIELD_D = fieldOffset("d");
    private static final MethodHandle GET_INT_A;
    private static final MethodHandle GET_INT_B;
    private static final MethodHandle GET_INT_C;
    private static final MethodHandle GET_INT_D;
    private static final MethodHandle PUT_INT_A;
    private static final MethodHandle PUT_INT_B;
    private static final MethodHandle PUT_INT_C;
    private static final MethodHandle PUT_INT_D;

    public static SmallDataClass copy(SmallDataClass original) throws Throwable {
        var copy = (SmallDataClass) ALLOCATE.invoke(UNSAFE, SmallDataClass.class);
        PUT_INT_A.invoke(copy, (int) GET_INT_A.invoke(original));
        PUT_INT_B.invoke(copy, (int) GET_INT_B.invoke(original));
        PUT_INT_C.invoke(copy, (int) GET_INT_C.invoke(original));
        PUT_INT_D.invoke(copy, (int) GET_INT_D.invoke(original));
        return copy;
    }

    static {
        try {
            var field = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            forceAccess(field);
            var lookup = (MethodHandles.Lookup) field.get(null);
            ALLOCATE = lookup.unreflect(Class.forName("jdk.internal.misc.Unsafe").getDeclaredMethod("allocateInstance", Class.class));
            GET_INT = lookup.unreflect(Class.forName("jdk.internal.misc.Unsafe").getDeclaredMethod("getInt", Object.class, long.class)).bindTo(UNSAFE);
            PUT_INT = lookup.unreflect(Class.forName("jdk.internal.misc.Unsafe").getDeclaredMethod("putInt", Object.class, long.class, int.class)).bindTo(UNSAFE);
            GET_INT_A = MethodHandles.insertArguments(GET_INT, 1, FIELD_A);
            GET_INT_B = MethodHandles.insertArguments(GET_INT, 1, FIELD_B);
            GET_INT_C = MethodHandles.insertArguments(GET_INT, 1, FIELD_C);
            GET_INT_D = MethodHandles.insertArguments(GET_INT, 1, FIELD_D);
            PUT_INT_A = MethodHandles.insertArguments(PUT_INT, 1, FIELD_A);
            PUT_INT_B = MethodHandles.insertArguments(PUT_INT, 1, FIELD_B);
            PUT_INT_C = MethodHandles.insertArguments(PUT_INT, 1, FIELD_C);
            PUT_INT_D = MethodHandles.insertArguments(PUT_INT, 1, FIELD_D);
        } catch (NoSuchMethodException | IllegalAccessException | ClassNotFoundException | NoSuchFieldException e) {
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


    private static long fieldOffset(String name) {
        try {
            var offset = Class.forName("jdk.internal.misc.Unsafe").getDeclaredMethod("objectFieldOffset", Class.class, String.class);
            forceAccess(offset);
            return (long) offset.invoke(UNSAFE, SmallDataClass.class, name);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
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
