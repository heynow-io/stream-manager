package io.heynow.stream.manager.client.facade;

import io.heynow.stream.manager.client.model.ProcessingModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@FeignClient(url = "${service.stream-manager.url:}", name = "stream-manager")
public interface StreamManagerClient {

    @RequestMapping(method = RequestMethod.GET, value = "/processing-models/{source}/{eventType}")
    List<ProcessingModel> getProcessingModels(@PathVariable("source") String source, @PathVariable("eventType") String eventType);

    @RequestMapping(method = RequestMethod.GET, value = "/operators/{id}/properties", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> getProperties(@PathVariable("id") Long id);
}
