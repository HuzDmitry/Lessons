package by.Threads.WaitNotify.brocker;

import by.Threads.WaitNotify.consumer.MessageConsumingTask;
import by.Threads.WaitNotify.model.Message;
import by.Threads.WaitNotify.producer.MessageProducingTask;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

public final class MessageBroker {
    private static final String MESSAGE_OF_MESSAGE_IS_PRODUCED = "Message '%s' is produced by name '%s'. Size of '%d'\n";
    private static final String TEMPLATE_MESSAGE_OF_MESSAGE = "Message '%s' is consumed by name '%s'. Size of '%d'\n";
    private final Queue<Message> messagesToConsumed;
    private  final int maxStoredMessages;

    public MessageBroker(final int maxStoredMessages) {
        this.messagesToConsumed = new ArrayDeque<>(maxStoredMessages);
        this.maxStoredMessages = maxStoredMessages;
    }
    public synchronized void produce(final Message message, final MessageProducingTask messageProducingTask){
        try {
            while (!isShouldProduce(messageProducingTask)){
                super.wait();
            }
            messagesToConsumed.add(message);
            System.out.printf(MESSAGE_OF_MESSAGE_IS_PRODUCED, message, messageProducingTask.getName(),
                    messagesToConsumed.size()-1);
            super.notify();
        }catch (InterruptedException o){
            Thread.currentThread().interrupt();
        }
    }
    public synchronized Optional<Message> consume(final MessageConsumingTask messageConsumingTask){
        try {
            while (!isShouldConsume(messageConsumingTask)){
                super.wait();
            }
            Message message = messagesToConsumed.poll();
            System.out.printf(TEMPLATE_MESSAGE_OF_MESSAGE,message, messageConsumingTask.getName(),
                    messagesToConsumed.size() + 1);
            super.notify();
            return Optional.ofNullable(message);
        }catch (InterruptedException o){
            Thread.currentThread().interrupt();
            return Optional.empty();
        }
    }
    private boolean isShouldConsume(final MessageConsumingTask messageConsumingTask){
        return !messagesToConsumed.isEmpty() && messagesToConsumed.size() >= messageConsumingTask.getMinAmountMessageToConsume();
    }
    private boolean isShouldProduce(final MessageProducingTask messageProducingTask){
        return messagesToConsumed.size() < maxStoredMessages && messagesToConsumed.size() <= messageProducingTask.getMaxAmountMessageToProduce();
    }
}
