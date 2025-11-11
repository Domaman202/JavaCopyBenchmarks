package ru.DmN.JavaTest;

import org.openjdk.jmh.annotations.*;
import ru.DmN.JavaTest.known.big.*;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@Threads(Threads.MAX)
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 1, time = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.Throughput)
public class KnownBigBench {
    @Benchmark
    public BigDataClass objectClone() throws CloneNotSupportedException {
        return ObjectClone.copy(BigDataClass.create());
    }

    @Benchmark
    public BigDataClass handsInit() {
        return HandsInit.copy(BigDataClass.create());
    }

    @Benchmark
    public BigDataClass constructorInit() {
        return ConstructorInit.copy(BigDataClass.create());
    }

    @Benchmark
    public BigDataClass reflectionAccess() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        return ReflectionAccess.copy(BigDataClass.create());
    }

    @Benchmark
    public BigDataClass varHandleAccess() throws Throwable {
        return VarHandleAccess.copy(BigDataClass.create());
    }

    @Benchmark
    public BigDataClass sunUnsafeAccess() throws Throwable {
        return SunUnsafeAccess.copy(BigDataClass.create());
    }

    @Benchmark
    public BigDataClass intUnsafeAccess() throws Throwable {
        return IntUnsafeAccess.copy(BigDataClass.create());
    }

    @Benchmark
    public BigDataClass intUnsafeBlockCopy() throws Throwable {
        return IntUnsafeBlockCopy.copy(BigDataClass.create());
    }
}