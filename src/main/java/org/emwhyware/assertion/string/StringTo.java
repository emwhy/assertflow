package org.emwhyware.assertion.string;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;
import org.emwhyware.assertion.Connector;

public class StringTo extends Connector {
    public final StringConditions to;

    protected StringTo(@Nullable AssertionGroup group, @NonNull String labelForActual, @Nullable String actual, boolean negated, boolean ignoreCase) {
        super(group, labelForActual);
        this.to = new StringConditions(group, labelForActual, actual, negated, ignoreCase);
    }
}
