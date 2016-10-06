package io.heynow.stream.manager.client.web.controller;

import io.heynow.stream.manager.client.web.service.StreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
@RestController
@RequestMapping(("/operators"))
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class OperatorController {

    private final StreamService streamService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/properties")
    public Map<String, Object> getProperties(@PathVariable("id") Long id) {
        return streamService.getProperties(id);
    }
}
