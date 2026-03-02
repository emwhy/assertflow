package org.emwhyware.assertion.bool;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;
import org.emwhyware.assertion.Conditions;

public final class BooleanConditions extends Conditions {
    private final boolean actual;

    BooleanConditions(@Nullable AssertionGroup group, @NonNull String labelForActual, boolean actual, boolean negated) {
        super(group, labelForActual, negated, false);
        this.actual = actual;
    }

    public void beTrue() {
        assertCondition(partialAssertionErrorMessage() + "to be true.", () -> {
            return negated != actual;
        });
    }

    private String partialAssertionErrorMessage() {
        if (labelForActual.isEmpty()) {
            return "Expected '" + actual + "'" + (negated?" not":"") + " ";
        } else {
            return "Expected actual value('" + actual + "') of '" + labelForActual + "'" + (negated?" not":"") + " ";
        }
    }
}
