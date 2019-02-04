package org.apache.camel.beanbinding;

import org.apache.camel.Body;
import org.apache.camel.Header;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Assert;
import org.junit.Test;

public class ParameterBoundByAnnotationTest extends CamelTestSupport {

    @Test
    public void bodyAndHeaderAreMappedWithAnnotation() {
        Assert.assertEquals("Let's rock !!!", template.requestBodyAndHeader("direct:concat", "Let's ", "suffix", "rock !!!"));
    }

    public static class Bean {
        public String concat(@Body String left, @Header("suffix") String right) {
            return left + right;
        }
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:concat").bean(Bean.class, "concat");
            };
        };
    }
}
