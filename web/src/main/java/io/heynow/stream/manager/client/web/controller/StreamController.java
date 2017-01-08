package io.heynow.stream.manager.client.web.controller;

import io.heynow.stream.manager.client.web.domain.Stream;
import io.heynow.stream.manager.client.web.service.StreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/streams")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class StreamController {

    private final StreamService streamService;

    @RequestMapping(method = RequestMethod.POST)
    public Stream createStream(@RequestBody Stream stream) {
        return streamService.create(stream);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Stream> getStreams() {
        return streamService.getStreams();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Stream getStream(@PathVariable("id") Long id) {
        return streamService.getStream(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteStream(@PathVariable("id") Long id) {
        streamService.deleteStream(id);
    }

}
