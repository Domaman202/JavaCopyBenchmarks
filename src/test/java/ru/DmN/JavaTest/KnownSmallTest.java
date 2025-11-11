package ru.DmN.JavaTest;

import org.junit.jupiter.api.Test;
import ru.DmN.JavaTest.known.small.*;

import java.lang.reflect.InvocationTargetException;

public class KnownSmallTest {
    @Test
    public void objectCloneTest() throws CloneNotSupportedException {
        var original = new SmallDataClass(12, 21, 202, 213);
        var copy = ObjectClone.copy(original);
        assert(original.equals(copy));
    }

    @Test
    public void handsInitTest() {
        var original = new SmallDataClass(12, 21, 202, 213);
        var copy = HandsInit.copy(original);
        assert(original.equals(copy));
    }

    @Test
    public void constructorInitTest() {
        var original = new SmallDataClass(12, 21, 202, 213);
        var copy = ConstructorInit.copy(original);
        assert(original.equals(copy));
    }

    @Test
    public void reflectionAccessTest() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        var original = new SmallDataClass(12, 21, 202, 213);
        var copy = ReflectionAccess.copy(original);
        assert(original.equals(copy));
    }

    @Test
    public void varHandleAccessTest() throws Throwable {
        var original = new SmallDataClass(12, 21, 202, 213);
        var copy = VarHandleAccess.copy(original);
        assert(original.equals(copy));
    }

    @Test
    public void sunUnsafeAccessTest() throws Throwable {
        var original = new SmallDataClass(12, 21, 202, 213);
        var copy = SunUnsafeAccess.copy(original);
        assert(original.equals(copy));
    }

    @Test
    public void intUnsafeAccessTest() throws Throwable {
        var original = new SmallDataClass(12, 21, 202, 213);
        var copy = IntUnsafeAccess.copy(original);
        assert(original.equals(copy));
    }

    @Test
    public void intUnsafeBlockCopyTest() throws Throwable {
        var original = new SmallDataClass(12, 21, 202, 213);
        var copy = IntUnsafeBlockCopy.copy(original);
        assert(original.equals(copy));
    }
}
