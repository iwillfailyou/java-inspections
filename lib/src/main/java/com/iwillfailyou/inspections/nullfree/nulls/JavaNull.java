package com.iwillfailyou.inspections.nullfree.nulls;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.iwillfailyou.inspections.nullfree.nulls.Null;
import com.iwillfailyou.javaparser.Description;
import com.iwillfailyou.javaparser.Item;
import com.iwillfailyou.javaparser.NodeItem;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class JavaNull implements Null {

    private final NullLiteralExpr expr;
    private final Item item;

    public JavaNull(final NullLiteralExpr expr, final Description description) {
        this(
            expr,
            new NodeItem(expr, description)
        );
    }

    public JavaNull(final NullLiteralExpr expr, final Item item) {
        this.expr = expr;
        this.item = item;
    }

    @Override
    public String description() {
        return item.description();
    }

    @Override
    public boolean isSuppressed() {
        return item.isSuppressed("nullfree");
    }

    @Override
    public boolean isInComparision() {
        final List<Boolean> result = Arrays.asList(false);
        final Optional<Node> optParent = expr.getParentNode();
        if (optParent.isPresent()) {
            final Node parent = optParent.get();
            final List<BinaryExpr> found = parent.findAll(BinaryExpr.class);
            if (found.size() == 1) {
                final BinaryExpr.Operator operator = found.get(0).getOperator();
                if (operator == BinaryExpr.Operator.NOT_EQUALS || operator == BinaryExpr.Operator.EQUALS) {
                    result.set(0, true);
                }
            }
        }
        return result.get(0);
    }
}
