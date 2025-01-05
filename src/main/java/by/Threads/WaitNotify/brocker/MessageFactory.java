package by.Threads.WaitNotify.brocker;

import by.Threads.WaitNotify.model.Message;

public final class MessageFactory {
        private static final int INITIAL_NEXT_MESSAGE_INDEX = 1;
        private static final String TEMPLATE_CREATED_MESSAGE_DATA = "Message#%d";
        private int nextMessageIndex;
        public MessageFactory(){
            nextMessageIndex=INITIAL_NEXT_MESSAGE_INDEX;
        }

        public Message create(){
            return new Message(String.format(TEMPLATE_CREATED_MESSAGE_DATA,incrementMessageIndex()));
        }
        private synchronized int incrementMessageIndex(){
            return nextMessageIndex++;
        }
}
