package by.Threads.WaitNotify.consumer;

import by.Threads.WaitNotify.brocker.MessageBroker;
import by.Threads.WaitNotify.model.Message;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class MessageConsumingTask implements Runnable {
    private static final int SECONDS_DURATION_TO_SLEEP_CONSUMER = 3;
    private final MessageBroker messageBroker;
    private final int minAmountMessageToConsume;
    private final String name;

    public MessageConsumingTask(final MessageBroker messageBroker, final int minAmountMessageToConsume,
                                final String name) {
        this.messageBroker = messageBroker;
        this.minAmountMessageToConsume= minAmountMessageToConsume;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMinAmountMessageToConsume() {
        return minAmountMessageToConsume;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try{
                TimeUnit.SECONDS.sleep(SECONDS_DURATION_TO_SLEEP_CONSUMER);
                final Optional<Message> optionalMessage = messageBroker.consume(this);
                optionalMessage.orElseThrow(MessageConsumingException::new);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
