package org.emwhyware.assertion.string;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;

public class StringIgnoringCaseOrTo extends StringToOrNot {
    public final StringToOrNot ignoringCase;

    protected StringIgnoringCaseOrTo(@Nullable AssertionGroup group, @NonNull String labelForActual, @Nullable String actual, boolean negated, boolean ignoreCase) {
        super(group, labelForActual, actual, negated, ignoreCase);

        this.ignoringCase = new StringToOrNot(group, labelForActual, actual, negated, true);
    }
}
