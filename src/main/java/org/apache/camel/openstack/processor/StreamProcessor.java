package org.apache.camel.openstack.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by EKabardinsky on 4/26/2016.
 */
public class StreamProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getIn().getBody(String.class);
        exchange.getOut().setBody(body);
    }
}
