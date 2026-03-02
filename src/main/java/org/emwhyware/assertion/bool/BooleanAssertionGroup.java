package org.emwhyware.assertion.bool;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.emwhyware.assertion.AssertionGroup;

public final class BooleanAssertionGroup {
    private final AssertionGroup group;

    public BooleanAssertionGroup(@NonNull AssertionGroup group) {
        this.group = group;
    }

    public BooleanToOrNot expect(boolean actual) {
        return expect("", actual);
    }

    public BooleanToOrNot expect(@NonNull String labelForActual, boolean actual) {
        return new BooleanToOrNot(group, labelForActual, actual, false);
    }
}
