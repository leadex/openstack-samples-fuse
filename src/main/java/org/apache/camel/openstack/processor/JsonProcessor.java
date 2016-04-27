package org.apache.camel.openstack.processor;

import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by EKabardinsky on 4/26/2016.
 */
public class JsonProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        Object body = exchange.getIn().getBody();

        exchange.getOut().setBody(gson.toJson(body));
    }
}
