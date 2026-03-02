package org.emwhyware.assertion.string;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;

public final class StringAssertionGroup {
    private final AssertionGroup group;

    public StringAssertionGroup(@NonNull AssertionGroup group) {
        this.group = group;
    }

    public StringIgnoringCaseOrTo expect(@Nullable String actual) {
        return expect("", actual);
    }

    public StringIgnoringCaseOrTo expect(@NonNull String labelForActual, @Nullable String actual) {
        return new StringIgnoringCaseOrTo(group, labelForActual, actual, false, false);
    }
}
