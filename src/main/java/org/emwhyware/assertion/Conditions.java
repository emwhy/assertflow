package org.emwhyware.assertion;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public abstract class Conditions {
    protected final AssertionGroup group;
    protected final String labelForActual;
    protected final boolean negated;
    protected final boolean ignoreCase;

    protected Conditions(@Nullable AssertionGroup group, @NonNull String labelForActual, boolean negated, boolean ignoreCase) {
        this.group = group;
        this.labelForActual = labelForActual;
        this.negated = negated;
        this.ignoreCase = ignoreCase;
    }

    protected interface Assertion {
        boolean getResult();
    }

    protected final void assertCondition(String errorMessage, Assertion assertion) {
        if (!assertion.getResult()) {
            if (group == null) {
                throw new AssertionError(errorMessage);
            } else  {
                this.addToGroup(new AssertionError(errorMessage));
            }
        }
    }

    protected final void addToGroup(Throwable throwable) {
        this.group.addThrowable(throwable);
    }
}
