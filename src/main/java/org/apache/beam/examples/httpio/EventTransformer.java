package org.apache.beam.examples.httpio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

/**
 * Creating a json from the given input
 */
public class EventTransformer extends DoFn<String, String> {
    private static final Logger logger = LoggerFactory.getLogger(EventTransformer.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @ProcessElement
    public void format(ProcessContext context, @Element String input) throws JsonProcessingException {
        logger.info("In Event Transformer.");
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("source","httpevent");
        objectNode.put("time", Instant.now().toEpochMilli());
        objectNode.put("event",input);

        String event = mapper.writeValueAsString(objectNode);
        logger.info("the event is: "+ event);
        context.output(
                event
        );
    }
}
