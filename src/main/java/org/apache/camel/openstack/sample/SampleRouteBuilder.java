package org.apache.camel.openstack.sample;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.ExpressionBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.openstack.processor.CreateVMProcessor;
import org.apache.camel.openstack.processor.JsonProcessor;
import org.apache.camel.openstack.processor.StreamProcessor;

/**
 * Created by EKabardinsky on 4/22/2016.
 */
public class SampleRouteBuilder extends RouteBuilder {
    public void configure() throws Exception {
        String credentials = "identity=admin:admin&password=1b8c575805da4ab9&provider=openstack-nova&region=RegionOne&novaUrl=http://10.0.0.8:5000/v2.0";
        restConfiguration().component("servlet").bindingMode(RestBindingMode.off);

        rest("/v1")
                .get("listFlavors").to("direct:flavor")
                .get("listImages").to("direct:image")
                .post("createVM").to("direct:createVM")
                .post("createKeyPair").to("direct:createKeyPair")
                .post("serverStatus").to("direct:serverStatus")
                .get("app").to("direct:app");

        from("direct:flavor")
                .setExchangePattern(ExchangePattern.InOut)
                .to("openstack://listFlavors?" + credentials)
                .process(new JsonProcessor());

        from("direct:image").setExchangePattern(ExchangePattern.InOut)
                .to("openstack://listImages?" + credentials)
                .process(new JsonProcessor());

        from("direct:createVM").setExchangePattern(ExchangePattern.InOut)
                .process(new CreateVMProcessor())
                .to("openstack://createVM?" + credentials)
                .process(new JsonProcessor());

        from("direct:createKeyPair").setExchangePattern(ExchangePattern.InOut)
                .process(new StreamProcessor())
                .to("openstack://createKeyPair?" + credentials)
                .process(new JsonProcessor());

        from("direct:serverStatus").setExchangePattern(ExchangePattern.InOut)
                .process(new StreamProcessor())
                .to("openstack://serverStatus?" + credentials)
                .process(new JsonProcessor());

        from("direct:app")
                .setBody()
                .simple("resource:classpath:index.html");
    }
}
