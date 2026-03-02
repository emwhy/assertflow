package org.emwhyware.assertion;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.bool.BooleanAssertionGroup;
import org.emwhyware.assertion.bool.BooleanToOrNot;
import org.emwhyware.assertion.collection.CollectionAssertionGroup;
import org.emwhyware.assertion.collection.CollectionIgnoringCaseOrTo;
import org.emwhyware.assertion.date.DateAssertionGroup;
import org.emwhyware.assertion.date.DateToOrNot;
import org.emwhyware.assertion.exception.AssertionGroupError;
import org.emwhyware.assertion.number.NumberAssertionGroup;
import org.emwhyware.assertion.number.NumberToOrNot;
import org.emwhyware.assertion.string.StringAssertionGroup;
import org.emwhyware.assertion.string.StringIgnoringCaseOrTo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class AssertionGroup {
    private final String groupName;
    private final List<Throwable> throwables = new ArrayList<>();

    public StringAssertionGroup string;

    private AssertionGroup(String groupName) {
        this.groupName = groupName;
    }

    public static void group(@NonNull GroupAction action) {
        group("", action);
    }

    public static void group(@NonNull String groupName, @NonNull GroupAction action) {
        final AssertionGroup assertionGroup = new AssertionGroup(groupName);

        try {
            action.doAssertions(assertionGroup.instantiateGroup());
        } finally {
            if (!assertionGroup.throwables.isEmpty()) {
                throw new AssertionGroupError(groupName, assertionGroup.throwables);
            }
        }
    }

    private Group instantiateGroup() {
        return new Group(this);
    }

    void addThrowable(@NonNull Throwable throwable) {
        this.throwables.add(throwable);
    }

    public class Group {
        private final StringAssertionGroup string;
        private final CollectionAssertionGroup collection;
        private final NumberAssertionGroup number;
        private final DateAssertionGroup date;
        private final BooleanAssertionGroup bool;

        private Group(AssertionGroup group) {
            this.string = new StringAssertionGroup(AssertionGroup.this);
            this.collection = new CollectionAssertionGroup(AssertionGroup.this);
            this.number = new NumberAssertionGroup(AssertionGroup.this);
            this.date = new DateAssertionGroup(AssertionGroup.this);
            this.bool = new BooleanAssertionGroup(AssertionGroup.this);
        }

        public StringIgnoringCaseOrTo expect(@Nullable String actual) {
            return expect("", actual);
        }

        public StringIgnoringCaseOrTo expect(@NonNull String labelForActual, @Nullable String actual) {
            return string.expect(labelForActual, actual);
        }


        public NumberToOrNot expect(int actual) {
            return expect(Integer.valueOf(actual));
        }

        public NumberToOrNot expect(long actual) {
            return expect(Long.valueOf(actual));
        }

        public NumberToOrNot expect(float actual) {
            return expect(Float.valueOf(actual));
        }

        public NumberToOrNot expect(double actual) {
            return expect(Double.valueOf(actual));
        }

        public NumberToOrNot expect(@NonNull String labelForActual, int actual) {
            return expect(labelForActual, Integer.valueOf(actual));
        }

        public NumberToOrNot expect(@NonNull String labelForActual, long actual) {
            return expect(labelForActual, Long.valueOf(actual));
        }

        public NumberToOrNot expect(@NonNull String labelForActual, float actual) {
            return expect(labelForActual, Float.valueOf(actual));
        }

        public NumberToOrNot expect(@NonNull String labelForActual, double actual) {
            return expect(labelForActual, Double.valueOf(actual));
        }

        public NumberToOrNot expect(@NonNull Number actual) {
            return expect("", actual);
        }

        public NumberToOrNot expect(@NonNull String labelForActual, @NonNull Number actual) {
            return number.expect(labelForActual, actual);
        }


        public DateToOrNot expect(@NonNull Date actual) {
            return expect("", actual.toLocalDate());
        }

        public DateToOrNot expect(@NonNull LocalDate actual) {
            return expect("", actual);
        }

        public DateToOrNot expect(@NonNull String labelForActual, @NonNull Date actual) {
            return expect(labelForActual, actual.toLocalDate());
        }

        public DateToOrNot expect(@NonNull String labelForActual, @NonNull LocalDate actual) {
            return date.expect(labelForActual, actual);
        }

        public CollectionIgnoringCaseOrTo expect(@NonNull Object[] actual) {
            return expect("", actual);
        }

        public CollectionIgnoringCaseOrTo expect(@NonNull String labelForActual, @NonNull Object[] actual) {
            return collection.expect(labelForActual, actual);
        }

        public CollectionIgnoringCaseOrTo expect(@NonNull Collection<?> actual) {
            return expect("", actual);
        }

        public CollectionIgnoringCaseOrTo expect(@NonNull String labelForActual, @NonNull Collection<?> actual) {
            return collection.expect(labelForActual, actual);
        }

        public BooleanToOrNot expect(boolean actual) {
            return expect("", actual);
        }

        public BooleanToOrNot expect(@NonNull String labelForActual, boolean actual) {
            return bool.expect(labelForActual, actual);
        }
    }

    public interface GroupAction {
        void doAssertions(Group g);
    }
}
