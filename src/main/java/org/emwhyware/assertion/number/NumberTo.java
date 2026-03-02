package org.emwhyware.assertion.number;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;
import org.emwhyware.assertion.Connector;
import org.emwhyware.assertion.bool.BooleanConditions;

public class NumberTo extends Connector {
    public final NumberConditions to;

    protected NumberTo(@Nullable AssertionGroup group, @NonNull String labelForActual, @NonNull Number actual, boolean negated) {
        super(group, labelForActual);
        this.to = new NumberConditions(group, labelForActual, actual, negated);
    }
}
