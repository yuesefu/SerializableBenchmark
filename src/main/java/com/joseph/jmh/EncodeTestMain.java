package com.joseph.jmh;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class EncodeTestMain {
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(SerializableEncodeTest.class.getSimpleName())
            .output("E:/Encode-Benchmark.log").forks(2).build();
        new Runner(options).run();
    }
}
