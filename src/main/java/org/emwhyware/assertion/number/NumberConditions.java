package org.emwhyware.assertion.number;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;
import org.emwhyware.assertion.Conditions;

public final class NumberConditions extends Conditions {
    private final Number actual;

    NumberConditions(@Nullable AssertionGroup group, @NonNull String labelForActual, @NonNull Number actual, boolean negated) {
        super(group, labelForActual, negated, false);
        this.actual = actual;
    }

    public void equal(int expected) {
        equal(Integer.valueOf(expected));
    }

    public void equal(long expected) {
        equal(Long.valueOf(expected));
    }

    public void equal(float expected) {
        equal(Float.valueOf(expected));
    }

    public void equal(double expected) {
        equal(Double.valueOf(expected));
    }

    public void equal(@NonNull Number expected) {
        assertCondition(partialAssertionErrorMessage() + "to equal '" + expected + "'.", () -> {
            if (actual instanceof Float || expected instanceof Float) {
                return (actual.floatValue() == expected.floatValue()) != negated;
            } else {
                return (actual.doubleValue() == expected.doubleValue()) != negated;
            }
        });
    }

    // --- beMoreThan ---
    public void beMoreThan(int expected) { beMoreThan(Integer.valueOf(expected)); }
    public void beMoreThan(long expected) { beMoreThan(Long.valueOf(expected)); }
    public void beMoreThan(float expected) { beMoreThan(Float.valueOf(expected)); }
    public void beMoreThan(double expected) { beMoreThan(Double.valueOf(expected)); }

    public void beMoreThan(@NonNull Number expected) {
        assertCondition(partialAssertionErrorMessage() + "to be more than '" + expected + "'.", () -> {
            if (actual instanceof Float || expected instanceof Float) {
                return (actual.floatValue() > expected.floatValue()) != negated;
            } else {
                return (actual.doubleValue() > expected.doubleValue()) != negated;
            }
        });
    }

    // --- beLessThan ---
    public void beLessThan(int expected) { beLessThan(Integer.valueOf(expected)); }
    public void beLessThan(long expected) { beLessThan(Long.valueOf(expected)); }
    public void beLessThan(float expected) { beLessThan(Float.valueOf(expected)); }
    public void beLessThan(double expected) { beLessThan(Double.valueOf(expected)); }

    public void beLessThan(@NonNull Number expected) {
        assertCondition(partialAssertionErrorMessage() + "to be less than '" + expected + "'.", () -> {
            if (actual instanceof Float || expected instanceof Float) {
                return (actual.floatValue() < expected.floatValue()) != negated;
            } else {
                return (actual.doubleValue() < expected.doubleValue()) != negated;
            }
        });
    }

    // --- beMoreThanOrEqual ---
    public void beMoreThanOrEqual(int expected) { beMoreThanOrEqual(Integer.valueOf(expected)); }
    public void beMoreThanOrEqual(long expected) { beMoreThanOrEqual(Long.valueOf(expected)); }
    public void beMoreThanOrEqual(float expected) { beMoreThanOrEqual(Float.valueOf(expected)); }
    public void beMoreThanOrEqual(double expected) { beMoreThanOrEqual(Double.valueOf(expected)); }

    public void beMoreThanOrEqual(@NonNull Number expected) {
        assertCondition(partialAssertionErrorMessage() + "to be more than or equal '" + expected + "'.", () -> {
            if (actual instanceof Float || expected instanceof Float) {
                return (actual.floatValue() >= expected.floatValue()) != negated;
            } else {
                return (actual.doubleValue() >= expected.doubleValue()) != negated;
            }
        });
    }

    // --- beLessThanOrEqual ---
    public void beLessThanOrEqual(int expected) { beLessThanOrEqual(Integer.valueOf(expected)); }
    public void beLessThanOrEqual(long expected) { beLessThanOrEqual(Long.valueOf(expected)); }
    public void beLessThanOrEqual(float expected) { beLessThanOrEqual(Float.valueOf(expected)); }
    public void beLessThanOrEqual(double expected) { beLessThanOrEqual(Double.valueOf(expected)); }

    public void beLessThanOrEqual(@NonNull Number expected) {
        assertCondition(partialAssertionErrorMessage() + "to be less than or equal '" + expected + "'.", () -> {
            if (actual instanceof Float || expected instanceof Float) {
                return (actual.floatValue() <= expected.floatValue()) != negated;
            } else {
                return (actual.doubleValue() <= expected.doubleValue()) != negated;
            }
        });
    }

    // --- beBetween ---
    public void beBetween(double lower, double upper) {
        beBetween(Double.valueOf(lower), Double.valueOf(upper));
    }

    public void beBetween(@NonNull Number expectedLower, @NonNull Number expectedUpper) {
        assertCondition(partialAssertionErrorMessage() + "to be between '" + expectedLower + "' and '" + expectedUpper + "'.", () -> {
            if (actual instanceof Float || expectedLower instanceof Float || expectedUpper instanceof Float) {
                final float val = actual.floatValue();
                return (val >= expectedLower.floatValue() && val <= expectedUpper.floatValue()) != negated;
            } else {
                final double val = actual.doubleValue();
                return (val >= expectedLower.doubleValue() && val <= expectedUpper.doubleValue()) != negated;
            }
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
