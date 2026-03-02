package org.emwhyware.assertion.number;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;
import org.emwhyware.assertion.bool.BooleanTo;

public class NumberToOrNot extends NumberTo {
    public final NumberTo not;

    protected NumberToOrNot(@Nullable AssertionGroup group, @NonNull String labelForActual, @NonNull Number actual, boolean negated) {
        super(group, labelForActual, actual, negated);
        this.not = new NumberTo(group, labelForActual, actual, !negated);
    }
}
