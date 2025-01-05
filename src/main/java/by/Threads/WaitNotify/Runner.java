package by.Threads.WaitNotify;

import by.Threads.WaitNotify.brocker.MessageBroker;
import by.Threads.WaitNotify.brocker.MessageFactory;
import by.Threads.WaitNotify.consumer.MessageConsumingTask;
import by.Threads.WaitNotify.producer.MessageProducingTask;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        final int brokerMaxMessages = 15;
        final MessageBroker messageBroker  = new MessageBroker(brokerMaxMessages);
        final MessageFactory messageFactory = new MessageFactory();

        final Thread firstProducingThred = new Thread(new MessageProducingTask(messageBroker, messageFactory,
                brokerMaxMessages, "PRODUCER_1"));
        final Thread secondProducingThred = new Thread(new MessageProducingTask(messageBroker, messageFactory,
                10, "PRODUCER_2"));
        final Thread thirdProducingThred = new Thread(new MessageProducingTask(messageBroker, messageFactory,
                5, "PRODUCER_3"));
        final Thread firstConsumingThred = new Thread(new MessageConsumingTask(messageBroker,
                0, "CONSUMER_1"));
        final Thread secondConsumingThred = new Thread(new MessageConsumingTask(messageBroker,
                6, "CONSUMER_2"));
        final Thread thirdConsumingThred = new Thread(new MessageConsumingTask(messageBroker,
                11, "CONSUMER_3"));

        startThreads(firstProducingThred, secondProducingThred, thirdProducingThred,
                firstConsumingThred, secondConsumingThred, thirdConsumingThred);
    }
    private static void startThreads(final Thread... threads){
        Arrays.stream(threads).forEach(Thread::start);
    }
}
