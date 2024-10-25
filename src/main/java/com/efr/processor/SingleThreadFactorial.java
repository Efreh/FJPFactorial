package com.efr.processor;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalTime;

// Класс для вычисления факториала в однопоточном режиме
public class SingleThreadFactorial {
    public static void runFactorial(int number) {
        BigInteger integer = BigInteger.ONE;

        LocalTime startTime = LocalTime.now(); // Начало замера времени

        for (int i = 1; i <= number; i++) {
            integer = integer.multiply(BigInteger.valueOf(i));
        }

        LocalTime endTime = LocalTime.now(); // Конец замера времени

        // Расчет времени выполнения
        long workTimeSec = Duration.between(startTime, endTime).toSeconds();
        long workTimeMillis = Duration.between(startTime, endTime).toMillis() % 1000;

        System.out.println("Время выполнения факториала одним потоком : "
                + (workTimeSec)
                + " секунд, " + (workTimeMillis) + " миллисекунд");
    }
}