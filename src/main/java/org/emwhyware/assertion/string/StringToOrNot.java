package org.emwhyware.assertion.string;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;

public class StringToOrNot extends StringTo {
    public final StringTo not;

    protected StringToOrNot(@Nullable AssertionGroup group, @NonNull String labelForActual, @Nullable String actual, boolean negated, boolean ignoreCase) {
        super(group, labelForActual, actual, negated, ignoreCase);
        this.not = new StringTo(group, labelForActual, actual, !negated, ignoreCase);
    }
}
