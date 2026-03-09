package org.emw.assertion.json;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emw.assertion.AssertionGroup;
import org.emw.assertion.collection.CollectionAssertionMethods;
import org.json.JSONArray;

import java.util.Collection;
import java.util.List;

public class JsonNodesAnyOrderOnlyAssertionMethods extends JsonNodesAssertionMethods {
    public final JsonNodesAssertionMethods inAnyOrder;

    JsonNodesAnyOrderOnlyAssertionMethods(@Nullable JSONArray actual, boolean negated, boolean ignoreCase, boolean anyOrder, @NonNull List<String> excludedNodes) {
        super(actual, negated, ignoreCase, anyOrder, excludedNodes);
        this.inAnyOrder = new JsonNodesAssertionMethods(actual, negated, ignoreCase, true, excludedNodes);
    }

    public JsonNodesAnyOrderOnlyAssertionMethods excluding(@NonNull String jsonPointer) {
        this.excludedNodes.add(jsonPointer);
        return this;
    }

}
