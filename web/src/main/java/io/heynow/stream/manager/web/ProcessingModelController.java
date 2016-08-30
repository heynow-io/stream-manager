package io.heynow.stream.manager.web;

import io.heynow.stream.manager.model.ProcessingModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/processing-models")
public class ProcessingModelController {

    @RequestMapping(method = RequestMethod.GET, value = "/{source}/{eventType}")
    private List<ProcessingModel> getProcessingModels(@PathVariable("source") String source, @PathVariable("eventType") String eventType) {
        return Collections.emptyList();
    }
}
