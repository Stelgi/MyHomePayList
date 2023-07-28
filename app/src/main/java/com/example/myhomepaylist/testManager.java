package com.example.myhomepaylist;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class testManager {
    private ExecutorService executor = Executors.newFixedThreadPool(3);
    private Handler handler = new Handler(Looper.getMainLooper());
    private DataBaseActivity dba;

    testManager(DataBaseActivity dba){
        this.dba = dba;
    }

    public interface ResultListener<T> {
        void onResult(T result);
    }

    public void executeTaskInThread(Runnable task, ResultListener<Long> resultListener) {
        executor.execute(() -> {
            // Выполнение задачи в фоновом потоке и получение результата
            long result = performDatabaseTask();

            // Отправляем результат в главный поток с помощью Handler
            handler.post(() -> resultListener.onResult(result));
        });
    }

//    public CompletableFuture<Long> executeTaskInThread() {
//        return CompletableFuture.supplyAsync(() -> {
//            // Выполнение задачи в фоновом потоке и получение результата
//            return performDatabaseTask();
//        });
//    }

    private long performDatabaseTask() {
        long r = 1L;
        for (long i = 2L; i <= 100L; i++){
            r *= i;
        }
        long temp = 0;
        long t1 = System.currentTimeMillis();

        while(temp != 10000) {
            long t2 = System.currentTimeMillis();
            temp = t2 - t1;
        }


        return 1;
    }
}
