package dev.youshallnotpass.inspections.allpublic.nonpublics;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import dev.youshallnotpass.javaparser.Item;
import dev.youshallnotpass.javaparser.NodeDescription;
import dev.youshallnotpass.javaparser.NodeItem;

import java.util.Optional;

public final class JavaNonpublic implements Nonpublic {

    private final Item item;

    public JavaNonpublic(
        final MethodDeclaration expr,
        final TypeDeclaration<?> root
    ) {
        this(
            new NodeItem(
                expr,
                new NodeDescription(expr.getName(), Optional.empty(), root)
            )
        );
    }

    public JavaNonpublic(
        final MethodDeclaration expr,
        final Modifier cause,
        final TypeDeclaration<?> root
    ) {
        this(new NodeItem(expr, new NodeDescription(expr, cause, root)));
    }

    public JavaNonpublic(final Item item) {
        this.item = item;
    }

    @Override
    public String description() {
        return item.description();
    }

    @Override
    public boolean isSuppressed() {
        return item.isSuppressed("allpublic");
    }
}
