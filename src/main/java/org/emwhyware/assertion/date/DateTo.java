package org.emwhyware.assertion.date;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;
import org.emwhyware.assertion.Connector;
import org.emwhyware.assertion.collection.CollectionConditions;

import java.time.LocalDate;
import java.util.Collection;

public class DateTo extends Connector {
    public final DateConditions to;

    protected DateTo(@Nullable AssertionGroup group, @NonNull String labelForActual, @NonNull LocalDate actual, boolean negated) {
        super(group, labelForActual);
        this.to = new DateConditions(group, labelForActual, actual, negated);
    }
}
