package io.heynow.stream.manager.client.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class NoteTest {

    private static final String CONSUMER_ONE = "1";
    private static final String CONSUMER_TWO = "2";

    @Test
    public void testCurrentConsumer() {
        Note note = getNote();
        Assert.assertEquals(CONSUMER_ONE, note.getProcessingModel().getCurrent().getName());
    }

    @Test
    public void testProceed() {
        Note note = getNote();
        Consumer consumer = note.proceed();
        Assert.assertEquals(CONSUMER_TWO, note.getProcessingModel().getCurrent().getName());
        Assert.assertEquals(CONSUMER_TWO, consumer.getName());
    }

    @Test(expected = IllegalStateException.class)
    public void testTooManyProceeds() {
        Note note = getNote();
        note.proceed();
        note.proceed();
    }

    private Note getNote() {
        Note note = new Note();
        ProcessingModel model = new ProcessingModel();
        model.setConsumers(Arrays.asList(
                new Consumer(CONSUMER_ONE, CONSUMER_ONE),
                new Consumer(CONSUMER_TWO, CONSUMER_TWO)
        ));
        note.setProcessingModel(model);
        return note;
    }
}
