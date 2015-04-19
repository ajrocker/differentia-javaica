## Command Line ##

Having  differentia-javaica jar in your classpath run:
```
$ java pl.ncdc.differentia.Differentia src1 src2
```

## JUnit ##

```
import static pl.ncdc.differentia.DifferentiaAssert.assertSourcesEqual;

...

    assertSourcesEqual("expected/com/company/Foo.java", "actual/com/company/Foo.java");
```

## Other apps ##

Use one of many `pl.ncdc.differentia.Differentia`'s compare methods.