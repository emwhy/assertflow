package org.emwhyware.assertion.string;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;
import org.emwhyware.assertion.Conditions;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public final class StringBeConditions extends Conditions {
    private final String actual;

    StringBeConditions(@Nullable AssertionGroup group, @NonNull String labelForActual, @Nullable String actual, boolean negated, boolean ignoreCase) {
        super(group, labelForActual, negated, ignoreCase);
        this.actual = actual;
    }


    public void oneOf(String... expectedTexts) {
        this.oneOf(Arrays.asList(expectedTexts));
    }

    public void oneOf(Collection<String> expectedTexts) {
        assertCondition(partialAssertionErrorMessage() + "to be one of '" + String.join("', '", expectedTexts) + "'.", () -> {
            final String testedActual = ignoreCase ? Optional.ofNullable(actual).orElse("").toLowerCase() : Optional.ofNullable(actual).orElse("");
            final Collection<String> testedExpectedTexts = ignoreCase ? expectedTexts.stream().map(String::toLowerCase).toList() : expectedTexts;

            return (actual != null && testedExpectedTexts.contains(testedActual)) != negated;
        });
    }

    public void empty() {
        assertCondition(partialAssertionErrorMessage() + "to be empty.", () -> {
            return actual != null && negated != actual.isEmpty();
        });
    }

    public void nullValue() {
        assertCondition(partialAssertionErrorMessage() + "to be null.", () -> {
            return negated != (actual == null);
        });
    }

    private String partialAssertionErrorMessage() {
        if (labelForActual.isEmpty() && ignoreCase) {
            return "Ignoring cases, expected '" + actual + "'" + (negated?" not":"") + " ";
        } else if (labelForActual.isEmpty() && !ignoreCase) {
            return "Expected '" + actual + "'" + (negated?" not":"") + " ";
        } else if (ignoreCase) {
            return "Ignoring cases, expected actual value('" + actual + "') of '" + labelForActual + "'" + (negated?" not":"") + " ";
        } else {
            return "Expected actual value('" + actual + "') of '" + labelForActual + "'" + (negated?" not":"") + " ";
        }
    }

}
