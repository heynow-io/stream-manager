package io.heynow.stream.manager.client.web.controller;

import io.heynow.stream.manager.client.model.ProcessingModel;
import io.heynow.stream.manager.client.web.service.StreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/processing-models")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ProcessingModelController {

    @Autowired
    private final StreamService streamService;

    @RequestMapping(method = RequestMethod.GET, value = "/{source}/{eventType}")
    private List<ProcessingModel> getProcessingModels(@PathVariable("source") String source, @PathVariable("eventType") String eventType) {
        return streamService.getProcessingModels(source, eventType);
    }
}
