package org.apache.camel.beanbinding;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Assert;
import org.junit.Test;

public class AParamBoundWithConverterTest extends CamelTestSupport {

    @Test
    public void stringBodyIsConvertedToIntegerParam() {
        Assert.assertEquals(49, template.requestBody("direct:square", "7"));
    }

    public static class Bean {
        public int square(int n) {
            return n * n;
        }
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:square").bean(Bean.class, "square");
            };
        };
    }
}
