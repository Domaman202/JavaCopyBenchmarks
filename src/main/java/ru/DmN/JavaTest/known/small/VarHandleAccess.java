package ru.DmN.JavaTest.known.small;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;

public class VarHandleAccess {
    private static final VarHandle FIELD_A;
    private static final VarHandle FIELD_B;
    private static final VarHandle FIELD_C;
    private static final VarHandle FIELD_D;
    private static final MethodHandle CONSTRUCTOR;

    public static SmallDataClass copy(SmallDataClass original) throws Throwable {
        var copy = (SmallDataClass) CONSTRUCTOR.invoke();
        // !!! ВАЖНО СОБЛЮДАТЬ СИГНАТУРУ !!!
        FIELD_A.set(copy, (int) FIELD_A.get(original));
        FIELD_B.set(copy, (int) FIELD_B.get(original));
        FIELD_C.set(copy, (int) FIELD_C.get(original));
        FIELD_D.set(copy, (int) FIELD_D.get(original));
        // !!! ВЫЗЫВАЕТ ЛИШНИЙ BOX / UNBOX !!!
//        FIELD_A.set(copy, FIELD_A.get(original));
//        FIELD_B.set(copy, FIELD_B.get(original));
//        FIELD_C.set(copy, FIELD_C.get(original));
//        FIELD_D.set(copy, FIELD_D.get(original));
        //
        return copy;
    }

    static {
        try {
            var lookup = MethodHandles.lookup();
            FIELD_A = lookup.findVarHandle(SmallDataClass.class, "a", int.class);
            FIELD_B = lookup.findVarHandle(SmallDataClass.class, "b", int.class);
            FIELD_C = lookup.findVarHandle(SmallDataClass.class, "c", int.class);
            FIELD_D = lookup.findVarHandle(SmallDataClass.class, "d", int.class);
            CONSTRUCTOR = lookup.findConstructor(SmallDataClass.class, MethodType.methodType(void.class));
        } catch (NoSuchMethodException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
