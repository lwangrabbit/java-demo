package com.example.demo.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

@Slf4j
public class ForkJoinTaskExample1 extends RecursiveTask<Integer> {

    public static final int threshold = 2;
    private int start;
    private int end;

    public ForkJoinTaskExample1(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        //如果任务足够小，就计算任务
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            //如果任务大于阈值，就分裂成两个子任务计算
            int mid = (start + end) / 2;
            ForkJoinTaskExample1 leftForkJoin = new ForkJoinTaskExample1(start, mid);
            ForkJoinTaskExample1 rightForkJoin = new ForkJoinTaskExample1(mid + 1, end);

            //执行子任务
            leftForkJoin.fork();
            rightForkJoin.fork();

            //等待任务执行结束合并其结果
            int leftResult = leftForkJoin.join();
            int rightResult = rightForkJoin.join();

            //合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        //生成一个计算任务
        ForkJoinTaskExample1 task = new ForkJoinTaskExample1(1, 100);

        //执行一个任务
        Future<Integer> future = pool.submit(task);

        try {
            log.info("result: {}", future.get());
        } catch(Exception err) {
            log.error("exception: {}", err);
        }
    }
}
