package com.learn.booking.bookingsystem.multithreading.advanced.task1;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;
import lombok.Getter;
import lombok.SneakyThrows;

@Getter
public class FactorialRecursiveTask extends RecursiveTask<BigInteger> {

    private BigInteger start;
    private BigInteger end;

    private static final BigInteger THRESHOLD = BigInteger.valueOf(10);

    public FactorialRecursiveTask(BigInteger start, BigInteger end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected BigInteger compute() {
        if (end.subtract(start).compareTo(THRESHOLD) <= 0) {
            return computeDirectly();
        } else {
            BigInteger mid = start.add(end.subtract(start).divide(BigInteger.valueOf(2)));
            FactorialRecursiveTask left = new FactorialRecursiveTask(start, mid);
            left.fork();
            FactorialRecursiveTask right = new FactorialRecursiveTask(mid.add(BigInteger.ONE), end);
            //or invokeAll(left, right) can be used. But then result variable should be added and RecursiveAction can be used
            invokeAll(left, right);
            return right.compute().multiply(left.join());
        }
    }

    private BigInteger computeDirectly() {
        BigInteger result = BigInteger.ONE;
        for (BigInteger i = start; i.compareTo(end) <= 0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(i);
        }
        return result;
    }

}
