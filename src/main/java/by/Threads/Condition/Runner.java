package by.Threads.Condition;

import java.util.concurrent.TimeUnit;

import static java.util.stream.Stream.iterate;

public class Runner {
    public static void main(String[] args) {
        final BoundedBuffer<Integer> buffer = new BoundedBuffer<>(5);
        final Runnable producingTask = ()-> iterate(0, i->i+1).forEach(i->{
            try {
                buffer.put(i);
                TimeUnit.SECONDS.sleep(1);
            }catch (final InterruptedException interruptedException){
                Thread.currentThread().interrupt();
            }
        });
        final Thread producingThread = new Thread(producingTask);
        final Runnable consumingTask = () ->{
            try {
                while (!Thread.currentThread().isInterrupted()){
                    buffer.take();
                    TimeUnit.SECONDS.sleep(3);
                }
            }catch (final InterruptedException interruptedException){
                Thread.currentThread().interrupt();
            }
        };
        final Thread consumingThread = new Thread(consumingTask);
        producingThread.start();
        consumingThread.start();
    }
}
