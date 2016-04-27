package org.apache.camel.openstack.processor;

import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.openstack.Model.CreateVMInfo;
import org.jclouds.openstack.nova.v2_0.options.CreateServerOptions;

/**
 * Created by EKabardinsky on 4/26/2016.
 */
public class CreateVMProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getIn().getBody(String.class);
        CreateVMInfo info = new Gson().fromJson(body, CreateVMInfo.class);

        CreateServerOptions options = CreateServerOptions.Builder.keyPairName(info.getKeyPair());
        Object[] params =  {info.getName(), info.getImage(), info.getFlavor(), options};

        exchange.getOut().setBody(params);
    }
}
