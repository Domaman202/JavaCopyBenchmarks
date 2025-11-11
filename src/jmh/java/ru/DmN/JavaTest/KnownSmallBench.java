package ru.DmN.JavaTest;

import org.openjdk.jmh.annotations.*;
import ru.DmN.JavaTest.known.small.*;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@Threads(Threads.MAX)
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 1, time = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.Throughput)
public class KnownSmallBench {
    @Benchmark
    public SmallDataClass objectClone() throws CloneNotSupportedException {
        return ObjectClone.copy(new SmallDataClass(12, 21, 202, 213));
    }

    @Benchmark
    public SmallDataClass handsInit() {
        return HandsInit.copy(new SmallDataClass(12, 21, 202, 213));
    }

    @Benchmark
    public SmallDataClass constructorInit() {
        return ConstructorInit.copy(new SmallDataClass(12, 21, 202, 213));
    }

    @Benchmark
    public SmallDataClass reflectionAccess() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        return ReflectionAccess.copy(new SmallDataClass(12, 21, 202, 213));
    }

    @Benchmark
    public SmallDataClass varHandleAccess() throws Throwable {
        return VarHandleAccess.copy(new SmallDataClass(12, 21, 202, 213));
    }

    @Benchmark
    public SmallDataClass sunUnsafeAccess() throws Throwable {
        return SunUnsafeAccess.copy(new SmallDataClass(12, 21, 202, 213));
    }

    @Benchmark
    public SmallDataClass intUnsafeAccess() throws Throwable {
        return IntUnsafeAccess.copy(new SmallDataClass(12, 21, 202, 213));
    }

    @Benchmark
    public SmallDataClass intUnsafeBlockCopy() throws Throwable {
        return IntUnsafeBlockCopy.copy(new SmallDataClass(12, 21, 202, 213));
    }
}