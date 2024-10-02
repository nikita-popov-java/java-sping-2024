package ru.nikitapopov.base.mod1;

import java.util.stream.IntStream;

public class Tasks {
    public void task1() {
        System.out.println("Сумма чисел от 0 до 1000, делющихся на 3 или 5 = " + IntStream.range(0, 1000)
                .filter(i -> i % 3 == 0 || i % 5 == 0)
                .sum());
    }

    public void task2() {
        int[][] x = { {20, 34, 2}, {9, 12, 18}, {3, 4, 5} };
        var min = Integer.MAX_VALUE;

        for (int i = 0; i < x.length; i++)
            for (int j = 0; j < x[i].length; j++)
                min = Math.min(x[i][j], min);

        System.out.println("Минимальный элемент = " + min);
    }
}
