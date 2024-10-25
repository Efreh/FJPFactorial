package com.efr.app;

import com.efr.processor.FactorialTask;
import com.efr.processor.SingleThreadFactorial;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolExample {
    public static void main(String[] args) {
        int numberForFactorial = 100000; // Число, для которого будет вычисляться факториал

        // Запуск однопоточного вычисления факториала
        SingleThreadFactorial.runFactorial(numberForFactorial);

        // Использование ForkJoinPool для многопоточного вычисления
        LocalTime startTime = LocalTime.now(); // Начало замера времени

        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            FactorialTask factorialTask = new FactorialTask(numberForFactorial);

            // Запускаем задачу и получаем результат
            BigInteger result = forkJoinPool.invoke(factorialTask);
        }

        LocalTime endTime = LocalTime.now(); // Конец замера времени

        // Расчет времени выполнения
        long workTimeSec = Duration.between(startTime, endTime).toSeconds();
        long workTimeMillis = Duration.between(startTime, endTime).toMillis() % 1000;

        System.out.println("Время выполнения факториала многопотоком : "
                + (workTimeSec)
                + " секунд, " + (workTimeMillis) + " миллисекунд");

        // Рекомендуется не выводить результат для больших чисел
        // System.out.println("Факториал " + numberForFactorial + "! = " + result);
    }
}
