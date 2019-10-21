package test.agency.services;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import test.api.MetricsDto;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MetricsService {

    // TODO: for horizontal scalability, use Redis or other messaging (ActiveMQ) and key/value storage (Couchbase)
    // TODO: consider serverless solution such as AWS Lambda, at least for controllers

    // This is a simple single-node implementation of in-memory data storage
    private final Map<String, MetricsDto> currentMetrics = new ConcurrentHashMap<>();
    // Historical metrics are not used by UI yet, however could be used for graphs
    private final Map<String, Queue<MetricsDto>> historicalMetrics = new ConcurrentHashMap<>();

    // TODO: either persist to DB using these queues or remove this and persist Redis
    private final Map<String, Queue<MetricsDto>> metricsToPersist = new ConcurrentHashMap<>();

    @Value("${view.buffer.capacity:50}")
    private int viewBufferCapacity;

    @Value("${persist.buffer.capacity:500}")
    private int persistBufferCapacity;

    public void putMetrics(String computerId, MetricsDto metrics) {
        // TODO: consider to use buffer queue between controller and services to improve scalability

        currentMetrics.put(computerId, metrics);
        historicalMetrics.computeIfAbsent(computerId, (id) -> new CircularFifoQueue<>(viewBufferCapacity)).add(metrics);
        metricsToPersist.computeIfAbsent(computerId, (id) -> new CircularFifoQueue<>(persistBufferCapacity)).add(metrics);
    }

    public Collection<String> getComputers() {
        // TODO: show last updated time
        return currentMetrics.keySet();
    }

    public MetricsDto getCurrentMetrics(String computerId) {
        return currentMetrics.get(computerId);
    }

    public Collection<MetricsDto> getHistoricalMetrics(String computerId) {
        return Collections.unmodifiableCollection(historicalMetrics.get(computerId));
    }
}
