package dev.youshallnotpass.inspections.allfinal.nonfinals;

import dev.youshallnotpass.inspection.InspectionException;
import dev.youshallnotpass.inspection.Violations;
import org.cactoos.iterable.Filtered;
import org.cactoos.list.ListOf;

import java.util.List;

public final class ExcludeLambdaParams implements Violations<Nonfinal> {

    private final Violations<Nonfinal> origin;

    public ExcludeLambdaParams(final Violations<Nonfinal> origin) {
        this.origin = origin;
    }

    @Override
    public List<Nonfinal> asList() throws InspectionException {
        return new ListOf<>(
            new Filtered<>(
                (final Nonfinal valuation) -> !valuation.isLambdaParam(),
                origin.asList()
            )
        );
    }
}
