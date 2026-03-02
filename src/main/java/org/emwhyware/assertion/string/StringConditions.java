package org.emwhyware.assertion.string;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;
import org.emwhyware.assertion.Conditions;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.regex.Pattern;

public final class StringConditions extends Conditions {
    private final String actual;

    StringConditions(@Nullable AssertionGroup group, @NonNull String labelForActual, @Nullable String actual, boolean negated, boolean ignoreCase) {
        super(group, labelForActual, negated, ignoreCase);
        this.actual = actual;
    }

    public void equal(@NonNull String expected) {
        assertCondition(partialAssertionErrorMessage() + "to equal '" + expected + "'.", () -> {
            final String testedActual = ignoreCase ? Optional.ofNullable(actual).orElse("").toLowerCase() : Optional.ofNullable(actual).orElse("");
            final String testedExpected = ignoreCase ? expected.toLowerCase() : expected;

            return actual != null && negated != testedActual.equals(testedExpected);
        });
    }

    public void contain(@NonNull String expected) {
        assertCondition(partialAssertionErrorMessage() + "to contain '" + expected + "'.", () -> {
            final String testedActual = ignoreCase ? Optional.ofNullable(actual).orElse("").toLowerCase() : Optional.ofNullable(actual).orElse("");
            final String testedExpected = ignoreCase ? expected.toLowerCase() : expected;

            return actual != null && negated != testedActual.contains(testedExpected);
        });

    }

    public void startWith(@NonNull String prefix) {
        assertCondition(partialAssertionErrorMessage() + "to start with '" + prefix + "'.", () -> {
            final String testedActual = ignoreCase ? Optional.ofNullable(actual).orElse("").toLowerCase() : Optional.ofNullable(actual).orElse("");
            final String testedExpected = ignoreCase ? prefix.toLowerCase() : prefix;

            return actual != null && negated != testedActual.startsWith(testedExpected);
        });

    }

    public void endWith(@NonNull String suffix) {
        assertCondition(partialAssertionErrorMessage() + "to end with '" + suffix + "'.", () -> {
            final String testedActual = ignoreCase ? Optional.ofNullable(actual).orElse("").toLowerCase() : Optional.ofNullable(actual).orElse("");
            final String testedExpected = ignoreCase ? suffix.toLowerCase() : suffix;

            return actual != null && negated != testedActual.endsWith(testedExpected);
        });

    }

    public void match(@NonNull String regex) {
        final Pattern pattern = Pattern.compile(regex);

        assertCondition(partialAssertionErrorMessage() + "to match the pattern '" + regex + "'.", () -> {
            return actual != null && negated != pattern.matcher(actual).matches();
        });
    }

    public void beOneOf(String... expectedTexts) {
        this.beOneOf(Arrays.asList(expectedTexts));
    }

    public void beOneOf(Collection<String> expectedTexts) {
        assertCondition(partialAssertionErrorMessage() + "to be one of '" + String.join("', '", expectedTexts) + "'.", () -> {
            final String testedActual = ignoreCase ? Optional.ofNullable(actual).orElse("").toLowerCase() : Optional.ofNullable(actual).orElse("");
            final Collection<String> testedExpectedTexts = ignoreCase ? expectedTexts.stream().map(String::toLowerCase).toList() : expectedTexts;

            return (actual != null && testedExpectedTexts.contains(testedActual)) != negated;
        });
    }

    public void beEmpty() {
        assertCondition(partialAssertionErrorMessage() + "to be empty.", () -> {
            return actual != null && negated != actual.isEmpty();
        });
    }

    public void beNull() {
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
