package by.Threads.WaitNotify.producer;

import by.Threads.WaitNotify.brocker.MessageBroker;
import by.Threads.WaitNotify.brocker.MessageFactory;
import by.Threads.WaitNotify.model.Message;
import java.util.concurrent.TimeUnit;

public final class MessageProducingTask implements Runnable {

    private static final int SECONDS_DURATION_TO_SLEEP = 1;
    private final MessageBroker messageBroker;
    private final MessageFactory messageFactory;
    private final int maxAmountMessageToProduce;
    private final String name;

    public MessageProducingTask(final MessageBroker messageBroker, final MessageFactory messageFactory,
                                final int maxAmountMessageToProduce, final String name) {
        this.messageBroker = messageBroker;
        this.messageFactory = messageFactory;
        this.maxAmountMessageToProduce = maxAmountMessageToProduce;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMaxAmountMessageToProduce() {
        return maxAmountMessageToProduce;
    }

    @Override
    public void run() {
      try{
          while(!Thread.currentThread().isInterrupted()){
              final Message producedMessage = messageFactory.create();
              TimeUnit.SECONDS.sleep(SECONDS_DURATION_TO_SLEEP);
              messageBroker.produce(producedMessage, this);
          }
      } catch (final InterruptedException e) {
          Thread.currentThread().interrupt();
      }
    }
}
