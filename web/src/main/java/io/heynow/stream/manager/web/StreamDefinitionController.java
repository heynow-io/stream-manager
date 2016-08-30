package io.heynow.stream.manager.web;

import io.heynow.stream.manager.model.StreamDefinition;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/streams")
public class StreamDefinitionController {

    @RequestMapping(method = RequestMethod.POST)
    public void createStream(@RequestBody StreamDefinition streamDefinition) {

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<StreamDefinition> getStreamDefinitions() {
        return Collections.emptyList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public StreamDefinition getStreamDefinition(@PathVariable("id") String id) {
        return Optional.of(new StreamDefinition())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

}
