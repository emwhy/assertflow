package org.emwhyware.assertion.bool;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;
import org.emwhyware.assertion.Connector;

public class BooleanTo extends Connector {
    public final BooleanConditions to;

    protected BooleanTo(@Nullable AssertionGroup group, @NonNull String labelForActual, boolean actual, boolean negated) {
        super(group, labelForActual);
        this.to = new BooleanConditions(group, labelForActual, actual, negated);
    }
}
