package ru.DmN.JavaTest.known.small;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflectionAccess {
    private static final Field FIELD_A = reflectField("a");
    private static final Field FIELD_B = reflectField("b");
    private static final Field FIELD_C = reflectField("c");
    private static final Field FIELD_D = reflectField("d");
    private static final Constructor<SmallDataClass> CONSTRUCTOR;

    public static SmallDataClass copy(SmallDataClass original) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        var copy = CONSTRUCTOR.newInstance();
        FIELD_A.set(copy, FIELD_A.get(original));
        FIELD_B.set(copy, FIELD_B.get(original));
        FIELD_C.set(copy, FIELD_C.get(original));
        FIELD_D.set(copy, FIELD_D.get(original));
        return copy;
    }

    static {
        try {
            CONSTRUCTOR = SmallDataClass.class.getDeclaredConstructor();
            CONSTRUCTOR.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static Field reflectField(String name) {
        try {
            var field = SmallDataClass.class.getDeclaredField(name);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
