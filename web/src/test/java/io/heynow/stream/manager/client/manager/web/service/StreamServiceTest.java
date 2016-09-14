package io.heynow.stream.manager.client.manager.web.service;

import io.heynow.stream.manager.client.manager.TestsBase;
import io.heynow.stream.manager.client.web.dao.NodeDao;
import io.heynow.stream.manager.client.web.domain.Stream;
import io.heynow.stream.manager.client.web.service.StreamService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StreamServiceTest extends TestsBase {

    @Autowired
    StreamService streamService;

    @Autowired
    NodeDao nodeDao;

    //TODO: implement tests
    @Test
    public void shouldCreateStream() {
        Stream stream = new Stream();
        stream.setName("some-stream");
        stream.setDescription("doing nothing really");
        stream.setRootNode(buildNewTree());

        streamService.create(stream);

//        streamService.getProperties()
//        streamService.deleteStream();
//        streamService.getProcessingModels()
//        streamService.getStreams()
//        streamService.getStream()
    }


}
