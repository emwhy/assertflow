package org.emwhyware.assertion.collection;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;
import org.emwhyware.assertion.Conditions;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class CollectionConditions extends Conditions {
    private final Collection<?> actualCollection;

    CollectionConditions(@Nullable AssertionGroup group, @NonNull String labelForActual, @NonNull Collection<?> actual, boolean negated, boolean ignoreCase) {
        super(group, labelForActual, negated, ignoreCase);
        this.actualCollection = actual;
    }

    public void beSameAs(Object[] expected) {
        this.beSameAs(Arrays.asList(expected));
    }

    public void beSameAs(Collection<?> expectedCollection) {
        assertCondition(partialAssertionErrorMessage() + "to be same as " + join(expectedCollection) + ".", () -> {
            final List<String> testActualList = ignoreCase ? actualCollection.stream().map(o -> o.toString().toLowerCase()).toList() : actualCollection.stream().map(Object::toString).toList();
            final List<String> testedExpectedList = ignoreCase ? expectedCollection.stream().map(o -> o.toString().toLowerCase()).toList() : expectedCollection.stream().map(Object::toString).toList();

            if (testActualList.size() != testedExpectedList.size()) {
                return negated;
            } else {
                for (int i = 0; i < testActualList.size(); i++) {
                    if (!testActualList.get(i).equals(testedExpectedList.get(i))) {
                        return negated;
                    }
                }
                return !negated;
            }
        });

    }

    public void beSameInAnyOrderAs(Object[] expected) {
        this.beSameInAnyOrderAs(Arrays.asList(expected));
    }

    public void beSameInAnyOrderAs(Collection<?> expectedCollection) {
        assertCondition(partialAssertionErrorMessage() + "to be same (in any order) as " + join(expectedCollection) + ".", () -> {
            final List<String> testActualList = ignoreCase ? actualCollection.stream().map(o -> o.toString().toLowerCase()).sorted().toList() : actualCollection.stream().map(Object::toString).sorted().toList();
            final List<String> testedExpectedList = ignoreCase ? expectedCollection.stream().map(o -> o.toString().toLowerCase()).sorted().toList() : expectedCollection.stream().map(Object::toString).sorted().toList();

            if (testActualList.size() != testedExpectedList.size()) {
                return negated;
            } else {
                for (int i = 0; i < testActualList.size(); i++) {
                    if (!testActualList.get(i).equals(testedExpectedList.get(i))) {
                        return negated;
                    }
                }
                return !negated;
            }
        });
    }

    public void haveSizeOf(int expectedSize) {
        assertCondition(partialAssertionErrorMessage() + "to have size of " + expectedSize + ", but was " + actualCollection.size() + ".", () -> {
            return (actualCollection.size() == expectedSize) != negated;
        });
    }

    public void have(Object... expected) {
        this.have(Arrays.asList(expected));
    }

    public void have(Collection<?> expectedCollection) {
        assertCondition(partialAssertionErrorMessage() + "to be same (in any order) as " + join(expectedCollection) + ".", () -> {
            final List<String> testActualList = ignoreCase ? actualCollection.stream().map(o -> o.toString().toLowerCase()).toList() : actualCollection.stream().map(Object::toString).toList();
            final List<String> testedExpectedList = ignoreCase ? expectedCollection.stream().map(o -> o.toString().toLowerCase()).toList() : expectedCollection.stream().map(Object::toString).toList();

            for (String expected : testedExpectedList) {
                if (!testActualList.contains(expected)) {
                    return negated;
                }
            }
            return !negated;
        });
    }

    private String partialAssertionErrorMessage() {
        if (labelForActual.isEmpty() && ignoreCase) {
            return "Ignoring cases, expected " + join(actualCollection) + (negated?" not":"") + " ";
        } else if (labelForActual.isEmpty() && !ignoreCase) {
            return "Expected " + join(actualCollection) + (negated?" not":"") + " ";
        } else if (ignoreCase) {
            return "Ignoring cases, expected actual value(" + join(actualCollection) + ") of '" + labelForActual + "'" + (negated?" not":"") + " ";
        } else {
            return "Expected actual value(" + join(actualCollection) + ") of '" + labelForActual + "'" + (negated?" not":"") + " ";
        }
    }

    private String join(Collection<?> collection) {
        return "'" + String.join(", ", collection.stream().map(Object::toString).toList()) + "'";
    }
}
