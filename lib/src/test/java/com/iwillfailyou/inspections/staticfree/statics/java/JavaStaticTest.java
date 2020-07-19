package com.iwillfailyou.inspections.staticfree.statics.java;

import com.iwillfailyou.inspections.staticfree.statics.Static;
import com.iwillfailyou.inspections.staticfree.statics.JavaStatics;
import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Test;

public class JavaStaticTest {
    @Test
    public void description() throws Exception {
        final Static aStatic = new JavaStatics(
            "interface A {\n",
            "    private static void a() {\n",
            "    }\n",
            "}\n"
        ).asList().get(0);
        Assert.assertThat(
            aStatic.description(),
            StringContains.containsString("A.a(A.java:2)")
        );
    }
}