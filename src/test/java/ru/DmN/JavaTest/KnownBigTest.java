package ru.DmN.JavaTest;

import org.junit.jupiter.api.Test;
import ru.DmN.JavaTest.known.big.*;

import java.lang.reflect.InvocationTargetException;

public class KnownBigTest {
    @Test
    public void objectCloneTest() throws CloneNotSupportedException {
        var original = BigDataClass.create();
        var copy = ObjectClone.copy(original);
        assert(original.equals(copy));
    }

    @Test
    public void handsInitTest() {
        var original = BigDataClass.create();
        var copy = HandsInit.copy(original);
        assert(original.equals(copy));
    }

    @Test
    public void constructorInitTest() {
        var original = BigDataClass.create();
        var copy = ConstructorInit.copy(original);
        assert(original.equals(copy));
    }

    @Test
    public void reflectionAccessTest() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        var original = BigDataClass.create();
        var copy = ReflectionAccess.copy(original);
        assert(original.equals(copy));
    }

    @Test
    public void varHandleAccessTest() throws Throwable {
        var original = BigDataClass.create();
        var copy = VarHandleAccess.copy(original);
        assert(original.equals(copy));
    }

    @Test
    public void sunUnsafeAccessTest() throws Throwable {
        var original = BigDataClass.create();
        var copy = SunUnsafeAccess.copy(original);
        assert(original.equals(copy));
    }

    @Test
    public void intUnsafeAccessTest() throws Throwable {
        var original = BigDataClass.create();
        var copy = IntUnsafeAccess.copy(original);
        assert(original.equals(copy));
    }

    @Test
    public void intUnsafeBlockCopyTest() throws Throwable {
        var original = BigDataClass.create();
        var copy = IntUnsafeBlockCopy.copy(original);
        assert(original.equals(copy));
    }
}
