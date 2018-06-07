package com.joseph.jmh;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class DecodeTestMain {
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(SerializableDecodeTest.class.getSimpleName()).output
            ("E:/Decode-Benchmark.log").forks(2).build();
        new Runner(options).run();
    }
}
