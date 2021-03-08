package com.marcsystem.spike.persistence;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;


@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class HolidaysServiceTest {

    private static HolidaysService holidaysService;
    private static HolidaysHybridService holidaysHybridService;

    @Autowired
    public void setHolidaysService(HolidaysService holidaysService) {
        HolidaysServiceTest.holidaysService = holidaysService;
    }

    @Autowired
    public static void setHolidaysHybridService(HolidaysHybridService holidaysHybridService) {
        HolidaysServiceTest.holidaysHybridService = holidaysHybridService;
    }

    @Benchmark
    public void testFindAll() {
        if (holidaysService != null) {
            List<Holidays> result = holidaysService.findAll();
            assertTrue(result.size() > 0);
        }
    }

    @Benchmark
    public void testFindAllHybrid() {
        if (holidaysHybridService != null) {
            List<HolidaysHybrid> result = holidaysHybridService.findAll();
            assertTrue(result.size() > 0);
        }
    }

    @Test
    public void runBenchmarks() throws Exception {
        Options opts = new OptionsBuilder()
                // set the class name regex for benchmarks to search for to the current class
                .include("\\." + this.getClass().getSimpleName() + "\\.")
                .warmupIterations(2)
                .measurementIterations(3)
                // do not use forking or the benchmark methods will not see references stored within its class
                .forks(0)
                // do not use multiple threads
                .threads(1)
                .shouldDoGC(true)
                .shouldFailOnError(true)
//                .resultFormat(ResultFormatType.JSON)
                .jvmArgs("-server")
                .build();

        new Runner(opts).run();
    }

}