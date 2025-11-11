# JavaCopyBenchmarks

Интересно, а какой самый быстрый способ скопировать данные из одного класса в другой есть в Java?...

```
Benchmark                                         Mode  Cnt       Score   Error   Units
DmN.JavaTest.KnownBigBench.constructorInit       thrpt         9072,225          ops/ms
DmN.JavaTest.KnownBigBench.handsInit             thrpt         8973,881          ops/ms
DmN.JavaTest.KnownBigBench.intUnsafeAccess       thrpt         3911,312          ops/ms
DmN.JavaTest.KnownBigBench.intUnsafeBlockCopy    thrpt         8852,350          ops/ms
DmN.JavaTest.KnownBigBench.objectClone           thrpt         8932,856          ops/ms
DmN.JavaTest.KnownBigBench.reflectionAccess      thrpt          539,566          ops/ms
DmN.JavaTest.KnownBigBench.sunUnsafeAccess       thrpt         9030,056          ops/ms
DmN.JavaTest.KnownBigBench.varHandleAccess       thrpt         9028,261          ops/ms
DmN.JavaTest.KnownSmallBench.constructorInit     thrpt       562386,270          ops/ms
DmN.JavaTest.KnownSmallBench.handsInit           thrpt       553297,781          ops/ms
DmN.JavaTest.KnownSmallBench.intUnsafeAccess     thrpt       215059,832          ops/ms
DmN.JavaTest.KnownSmallBench.intUnsafeBlockCopy  thrpt       282653,508          ops/ms
DmN.JavaTest.KnownSmallBench.objectClone         thrpt       550769,054          ops/ms
DmN.JavaTest.KnownSmallBench.reflectionAccess    thrpt       527451,112          ops/ms
DmN.JavaTest.KnownSmallBench.sunUnsafeAccess     thrpt       549342,463          ops/ms
DmN.JavaTest.KnownSmallBench.varHandleAccess     thrpt       551892,613          ops/ms
```
