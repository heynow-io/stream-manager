package io.heynow.stream.manager.client.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class NoteTest {

    private static final Long CONSUMER_ONE = 1L;
    private static final Long CONSUMER_TWO = 2L;

    @Test
    public void testCurrentConsumer() {
        Note note = getNote();
        Assert.assertEquals("One", note.getProcessingModel().getCurrent().getName());
    }

    @Test
    public void testProceed() {
        Note note = getNote();
        Consumer consumer = note.proceed();
        Assert.assertEquals("Two", note.getProcessingModel().getCurrent().getName());
        Assert.assertEquals("Two", consumer.getName());
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
                new Consumer(CONSUMER_ONE, "One"),
                new Consumer(CONSUMER_TWO, "Two")
        ));
        note.setProcessingModel(model);
        return note;
    }
}
