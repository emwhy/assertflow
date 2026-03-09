package org.emw.assertion.json;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emw.assertion.AssertionGroup;
import org.emw.assertion.collection.CollectionAssertionMethods;
import org.json.JSONArray;

import java.util.Collection;
import java.util.List;

public class JsonNodesNotOnlyAssertionMethods extends JsonNodesAssertionMethods {
    public final JsonNodesAssertionMethods not;

    JsonNodesNotOnlyAssertionMethods(@Nullable JSONArray actual, boolean negated, boolean ignoreCase, boolean anyOrder, @NonNull List<String> excludedNodes) {
        super(actual, negated, ignoreCase, anyOrder, excludedNodes);
        this.not = new JsonNodesAssertionMethods(actual, !negated, ignoreCase, anyOrder, excludedNodes);
    }

    public JsonNodesNotOnlyAssertionMethods excluding(@NonNull String jsonPointer) {
        this.excludedNodes.add(jsonPointer);
        return this;
    }
}
