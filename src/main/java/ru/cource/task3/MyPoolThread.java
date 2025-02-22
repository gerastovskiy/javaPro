package ru.cource.task3;

import lombok.SneakyThrows;

import java.util.List;
import java.util.Optional;

public class MyPoolThread extends Thread{
    private final List<Runnable> tasks;
    private volatile boolean shutdown = false;

    public MyPoolThread(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    public void setShutdown(){
        shutdown = true;
    }

    @Override
    public void run() {
        Optional<Runnable> task = Optional.empty();
        while (!shutdown) {
            synchronized (tasks) {
                if (!tasks.isEmpty()) {
                    task = Optional.of(tasks.removeFirst());
                } else {
                    task = Optional.empty();
                    try {
                        tasks.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            task.ifPresent(Runnable::run);
        }
    }
}