package dev.youshallnotpass.inspection.sources;

import dev.youshallnotpass.inspection.sources.java.JavaSourceMask;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Paths;

public final class ExcludeSourceMaskTest {

    @Test
    public void excludeTestsMask() {
        final SourceMask mask = new ExcludeSourceMask(
            new PathSourceMask("glob:**/test/**/*Test.java"),
            new JavaSourceMask()
        );
        Assert.assertThat(
            mask.matches(
                Paths.get(
                    ".",
                    "lib",
                    "src",
                    "test",
                    "java",
                    "com",
                    "example",
                    "MainTest.java"
                )
            ),
            IsEqual.equalTo(false)
        );
        Assert.assertThat(
            mask.matches(
                Paths.get(
                    ".",
                    "lib",
                    "src",
                    "test",
                    "java",
                    "com",
                    "example",
                    "Main.java"
                )
            ),
            IsEqual.equalTo(true)
        );
        Assert.assertThat(
            mask.matches(
                Paths.get(
                    ".",
                    "lib",
                    "src",
                    "main",
                    "java",
                    "com",
                    "example",
                    "Main.java"
                )
            ),
            IsEqual.equalTo(true)
        );
    }

    @Test
    public void excludeNothing() {
        final SourceMask mask = new ExcludeSourceMask(
            new PathSourceMask(),
            new JavaSourceMask()
        );
        Assert.assertThat(
            mask.matches(
                Paths.get(
                    ".",
                    "lib",
                    "src",
                    "test",
                    "java",
                    "com",
                    "example",
                    "MainTest.java"
                )
            ),
            IsEqual.equalTo(true)
        );
        Assert.assertThat(
            mask.matches(
                Paths.get(
                    ".",
                    "lib",
                    "src",
                    "test",
                    "java",
                    "com",
                    "example",
                    "Main.java"
                )
            ),
            IsEqual.equalTo(true)
        );
        Assert.assertThat(
            mask.matches(
                Paths.get(
                    ".",
                    "lib",
                    "src",
                    "main",
                    "java",
                    "com",
                    "example",
                    "Main.java"
                )
            ),
            IsEqual.equalTo(true)
        );
    }

}