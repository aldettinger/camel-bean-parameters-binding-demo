package org.apache.camel.beanbinding;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Assert;
import org.junit.Test;

public class ParameterBoundFromRouteTest extends CamelTestSupport {

    @Test
    public void parametersAreBoundViaSimpleExpressionsFromRouteDefinition() {
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("hostname", "localhost");
        headers.put("port", "61616");
        String result = template.requestBodyAndHeaders("direct:buildUri", "", headers, String.class);
        Assert.assertEquals("http://localhost:61616", result);
    }

    public static class Bean {
        public String buildUri(String hostname, String port) {
            return "http://" + hostname + ":" + port;
        }
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:buildUri").bean(Bean.class, "buildUri(${headers.hostname},${headers.port})");
            };
        };
    }
}
