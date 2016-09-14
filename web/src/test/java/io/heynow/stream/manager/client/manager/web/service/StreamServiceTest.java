package io.heynow.stream.manager.client.manager.web.service;

import io.heynow.stream.manager.client.manager.TestsBase;
import io.heynow.stream.manager.client.model.ProcessingModel;
import io.heynow.stream.manager.client.web.dao.NodeDao;
import io.heynow.stream.manager.client.web.domain.Node;
import io.heynow.stream.manager.client.web.domain.Stream;
import io.heynow.stream.manager.client.web.service.StreamService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.MapEntry.entry;
import static org.assertj.core.groups.Tuple.tuple;

public class StreamServiceTest extends TestsBase {

    @Autowired
    StreamService streamService;

    @Autowired
    NodeDao nodeDao;

    @Test
    public void shouldCreateStreamAndObservables() {
        Stream stream = new Stream();
        stream.setName("some-stream");
        stream.setDescription("doing nothing really");
        stream.setRootNode(buildNewTree());

        streamService.create(stream);

        Optional<Stream> loadedStreamOptional = streamService.getStream(stream.getId());

        assertThat(loadedStreamOptional.isPresent()).isTrue();
        Stream loadedStream = loadedStreamOptional.get();
        assertThat(loadedStream.getName()).isEqualTo("some-stream");
        assertThat(loadedStream.getDescription()).isEqualTo("doing nothing really");
        assertThat(loadedStream.getRootNode().getOperator().getName()).isEqualTo("mailer");
        assertThat(loadedStream.getRootNode().getParents()).hasSize(1);
        Node merger = loadedStream.getRootNode().getParents().get(0);
        assertThat(merger.getOperator().getName()).isEqualTo("merger");
        assertThat(merger.getOperator().getProperties()).extracting("name", "value").contains(tuple("key", "foo.bar"));
        assertThat(merger.getParents()).hasSize(2);
        Node filter = merger.getParents().get(0);
        assertThat(filter.getOperator().getName()).isEqualTo("filter");
        assertThat(filter.getOperator().getProperties()).extracting("name", "value").contains(tuple("foo", ">5"));
        assertThat(filter.getParents()).hasSize(1);
        Node producer1 = filter.getParents().get(0);
        assertThat(producer1.getOperator().getEventSource()).isEqualTo("queue");
        assertThat(producer1.getOperator().getEventType()).isEqualTo("different");
        Node mapper = merger.getParents().get(1);
        assertThat(mapper.getParents()).hasSize(1);
        Node buffer = mapper.getParents().get(0);
        assertThat(buffer.getOperator().getProperties()).extracting("name", "value").contains(tuple("time", "5"));
        assertThat(buffer.getParents()).hasSize(1);
        Node producer2 = buffer.getParents().get(0);
        assertThat(producer2.getOperator().getEventSource()).isEqualTo("queue");
        assertThat(producer2.getOperator().getEventType()).isEqualTo("something");

        Map<String, Object> properties = streamService.getProperties(buffer.getOperator().getId());
        assertThat(properties).hasSize(1).contains(entry("time", "5"));

        List<ProcessingModel> processingModels = streamService.getProcessingModels("queue", "something");
        assertThat(processingModels).hasSize(1);
        assertThat(processingModels.get(0).getConsumers()).extracting("name").contains(null, "buffer", "mapper", "merger", "mailer");

        streamService.deleteStream(stream.getId());
    }


}
