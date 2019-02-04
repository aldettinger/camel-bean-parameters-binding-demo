package org.apache.camel.beanbinding;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Assert;
import org.junit.Test;

public class BuiltInCamelTypesAreAutoBoundTest extends CamelTestSupport {

    @Test
    public void messageAndContextAreAutoBound() {
        Assert.assertEquals("CAMEL 2.23.0", template.requestBody("direct:upperCase", "camel"));
    }

    public static class Bean {
        public String upperCase(org.apache.camel.Message m, org.apache.camel.CamelContext ctx) {
            return m.getBody(String.class).toUpperCase() + " " + ctx.getVersion();
        }
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:upperCase").bean(Bean.class, "upperCase");
            };
        };
    }
}
