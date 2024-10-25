package com.efr.processor;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

// Класс для вычисления факториала с использованием RecursiveTask
public class FactorialTask extends RecursiveTask<BigInteger> {
    private static final int THRESHOLD = 1000; // Порог для переключения на однопоточный режим
    private final int numberForFactorial; // Число, для которого будет вычисляться факториал

    public FactorialTask(int numberForFactorial) {
        this.numberForFactorial = numberForFactorial;
    }

    @Override
    protected BigInteger compute() {
        // Если число меньше или равно порогу, вычисляем факториал последовательно
        if (numberForFactorial <= THRESHOLD) {
            BigInteger integer = BigInteger.ONE;
            for (int i = 1; i <= numberForFactorial; i++) {
                integer = integer.multiply(BigInteger.valueOf(i));
            }
            return integer;
        } else {
            // Разделяем задачу на две подзадачи
            int mid = numberForFactorial / 2;
            FactorialTask leftTask = new FactorialTask(mid);
            FactorialTask rightTask = new FactorialTask(numberForFactorial - mid);

            // Запускаем обе подзадачи параллельно
            leftTask.fork();
            rightTask.fork();

            // Ожидаем завершения обеих подзадач и объединяем результаты
            BigInteger leftResult = leftTask.join();
            BigInteger rightResult = rightTask.join();

            return leftResult.multiply(rightResult); // Умножаем результаты для получения факториала
        }
    }
}