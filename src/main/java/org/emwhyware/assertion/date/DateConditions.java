package org.emwhyware.assertion.date;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;
import org.emwhyware.assertion.Conditions;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConditions extends Conditions {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate actual;

    protected DateConditions(@Nullable AssertionGroup group, @NonNull String labelForActual, LocalDate actual, boolean negated) {
        super(group, labelForActual, negated, false);
        this.actual = actual;
    }

    public void beSameDate(@NonNull Date date) {
        this.beSameDate(date.toLocalDate());
    }

    public void beSameDate(@NonNull LocalDate expected) {
        assertCondition(partialAssertionErrorMessage() + "to be the same date as '" + expected.format(FORMATTER) + "'.", () -> {
            return actual.isEqual(expected) != negated;
        });
    }

    public void beBefore(@NonNull Date date) {
        this.beBefore(date.toLocalDate());
    }

    public void beBefore(@NonNull LocalDate expected) {
        assertCondition(partialAssertionErrorMessage() + "to be before '" + expected.format(FORMATTER) + "'.", () -> {
            return actual.isBefore(expected) != negated;
        });
    }

    public void beAfter(@NonNull Date date) {
        this.beAfter(date.toLocalDate());
    }

    public void beAfter(@NonNull LocalDate expected) {
        assertCondition(partialAssertionErrorMessage() + "to be after '" + expected.format(FORMATTER) + "'.", () -> {
            return actual.isAfter(expected) != negated;
        });
    }

    public void beSameOrBefore(@NonNull Date date) {
        this.beSameOrBefore(date.toLocalDate());
    }

    public void beSameOrBefore(@NonNull LocalDate expected) {
        assertCondition(partialAssertionErrorMessage() + "to be the same or before '" + expected.format(FORMATTER) + "'.", () -> {
            return actual.isEqual(expected) || actual.isBefore(expected);
        });
    }

    public void beSameOrAfter(@NonNull Date date) {
        this.beSameOrAfter(date.toLocalDate());
    }

    public void beSameOrAfter(@NonNull LocalDate expected) {
        assertCondition(partialAssertionErrorMessage() + "to be the same or after '" + expected.format(FORMATTER) + "'.", () -> {
            return (actual.isEqual(expected) || actual.isAfter(expected)) != negated;
        });
    }

    public void beBetween(@NonNull Date date1, @NonNull Date date2) {
        this.beBetween(date1.toLocalDate(), date2.toLocalDate());
    }

    public void beBetween(@NonNull LocalDate start, @NonNull LocalDate end) {
        assertCondition(partialAssertionErrorMessage() + "to be between '" + start.format(FORMATTER) + "' and '" + end.format(FORMATTER) + "'.", () -> {
            return (actual.isEqual(start) || (actual.isAfter(start) && actual.isBefore(end)) || actual.isEqual(end)) != negated;
        });
    }

    private String partialAssertionErrorMessage() {
        if (labelForActual.isEmpty()) {
            return "Expected '" + actual.format(FORMATTER) + "'" + (negated?" not":"") + " ";
        } else {
            return "Expected actual value('" + actual.format(FORMATTER) + "') of '" + labelForActual + "'" + (negated?" not":"") + " ";
        }
    }

}
