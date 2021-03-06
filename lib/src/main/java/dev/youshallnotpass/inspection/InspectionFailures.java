package dev.youshallnotpass.inspection;

import dev.youshallnotpass.inspection.badge.Badge;
import dev.youshallnotpass.plugin.Failures;
import dev.youshallnotpass.plugin.YsnpException;
import dev.youshallnotpass.plugin.Ui;

import java.net.URL;

public final class InspectionFailures<T extends Violation> implements Failures {

    private final Violations<T> nulls;
    private final Badge badge;

    public InspectionFailures(
        final Violations<T> nulls,
        final Badge badge
    ) {
        this.nulls = nulls;
        this.badge = badge;
    }

    @Override
    public void failIfRed() throws YsnpException {
        try {
            badge.failIfRed();
        } catch (final InspectionException e) {
            throw new YsnpException("Inspection failed. ", e);
        }
    }

    @Override
    public void show(final Ui ui) throws YsnpException {
        try {
            for (final T violation : nulls.asList()) {
                ui.println(violation.description());
            }
        } catch (final InspectionException e) {
            throw new YsnpException("Could not show the violations.", e);
        }
    }

    @Override
    public void publish(final URL url) throws YsnpException {
        try {
            badge.send(url);
        } catch (final InspectionException e) {
            throw new YsnpException(
                "Could not publish the violations.",
                e
            );
        }
    }
}
