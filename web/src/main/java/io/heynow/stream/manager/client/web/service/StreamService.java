package io.heynow.stream.manager.client.web.service;

import io.heynow.stream.manager.client.model.Consumer;
import io.heynow.stream.manager.client.model.ProcessingModel;
import io.heynow.stream.manager.client.web.dao.NodeDao;
import io.heynow.stream.manager.client.web.dao.ObservableDao;
import io.heynow.stream.manager.client.web.dao.OperatorDao;
import io.heynow.stream.manager.client.web.dao.StreamDao;
import io.heynow.stream.manager.client.web.domain.Node;
import io.heynow.stream.manager.client.web.domain.Observable;
import io.heynow.stream.manager.client.web.domain.Property;
import io.heynow.stream.manager.client.web.domain.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class StreamService {

    private final ObservableDao observableDao;
    private final StreamDao streamDao;
    private final TreeService treeService;
    private final OperatorDao operatorDao;
    private final NodeDao nodeDao;

    public List<Stream> getStreams() {
        return streamDao.findAll();
    }

    @Transactional
    public Stream create(Stream stream) {
        treeService.walkTreeFromLeafs(stream.getRootNode(), nodeDao::save);
        streamDao.save(stream);
        prepareObservables(stream).forEach(observableDao::save);
        return stream;
    }

    public Map<String, Object> getProperties(Long operatorId) {
        return operatorDao.findOne(operatorId).getProperties().stream()
                .collect(toMap(Property::getName, Property::getValue));
    }

    public Optional<Stream> getStream(Long id) {
        return Optional.ofNullable(streamDao.findOne(id));
    }

    public List<ProcessingModel> getProcessingModels(String source, String eventType) {
        return observableDao.findBySourceAndEventType(source, eventType).stream()
                .map(this::prepareProcessingModel)
                .collect(toList());
    }

    @Transactional
    public void deleteStream(Long id) {
        getStream(id).ifPresent(stream -> {
            observableDao.deleteByStream(stream);
            treeService.walkTreeFromLeafs(stream.getRootNode(), nodeDao::delete);
            streamDao.delete(stream);
        });
    }

    private ProcessingModel prepareProcessingModel(Observable observable) {
        List<Node> pathFromLeaf = treeService.findPathFromLeaf(observable.getStream().getRootNode(), observable);
        ProcessingModel model = new ProcessingModel();
        model.setStreamId(observable.getStream().getId());
        model.setEventSource(observable.getSource());
        model.setEventType(observable.getEventType());
        model.setConsumers(pathFromLeaf.stream()
                .map(this::prepareConsumer)
                .collect(toList())
        );
        return model;
    }

    private Consumer prepareConsumer(Node node) {
        Consumer consumer = new Consumer();
        consumer.setId(node.getOperator().getId());
        consumer.setName(node.getOperator().getName());
        return consumer;
    }

    private List<Observable> prepareObservables(Stream stream) {
        return treeService.findLeafs(stream.getRootNode()).stream()
                .map(leaf -> prepareObservable(stream, leaf))
                .collect(toList());
    }

    private Observable prepareObservable(Stream stream, Node node) {
        Observable observable = new Observable();
        observable.setEventType(node.getOperator().getEventType());
        observable.setSource(node.getOperator().getEventSource());
        observable.setStream(stream);
        return observable;
    }
}
