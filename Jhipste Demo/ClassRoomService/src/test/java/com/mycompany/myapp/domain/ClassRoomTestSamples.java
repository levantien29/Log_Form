package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ClassRoomTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ClassRoom getClassRoomSample1() {
        return new ClassRoom().id(1L).className("className1").department("department1").year(1).teacherId(1L);
    }

    public static ClassRoom getClassRoomSample2() {
        return new ClassRoom().id(2L).className("className2").department("department2").year(2).teacherId(2L);
    }

    public static ClassRoom getClassRoomRandomSampleGenerator() {
        return new ClassRoom()
            .id(longCount.incrementAndGet())
            .className(UUID.randomUUID().toString())
            .department(UUID.randomUUID().toString())
            .year(intCount.incrementAndGet())
            .teacherId(longCount.incrementAndGet());
    }
}
