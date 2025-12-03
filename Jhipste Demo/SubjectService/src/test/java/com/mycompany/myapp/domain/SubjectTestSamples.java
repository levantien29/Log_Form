package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SubjectTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Subject getSubjectSample1() {
        return new Subject().id(1L).subjectName("subjectName1").credit(1).teacherId(1L);
    }

    public static Subject getSubjectSample2() {
        return new Subject().id(2L).subjectName("subjectName2").credit(2).teacherId(2L);
    }

    public static Subject getSubjectRandomSampleGenerator() {
        return new Subject()
            .id(longCount.incrementAndGet())
            .subjectName(UUID.randomUUID().toString())
            .credit(intCount.incrementAndGet())
            .teacherId(longCount.incrementAndGet());
    }
}
