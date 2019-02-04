package org.apache.camel.beanbinding;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.language.Simple;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Assert;
import org.junit.Test;

public class ParameterBoundByLanguageAnnotationTest extends CamelTestSupport {

    @Test
    public void userNameParamValuedToCurrentUserViaLanguageAnnotation() {
        Assert.assertEquals("alex", template.requestBody("direct:userName", ""));
    }

    public static class Bean {
        public String userName(@Simple("${sysenv.USER}") String userName) {
            return userName;
        }
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:userName").bean(Bean.class, "userName");
            };
        };
    }
}
