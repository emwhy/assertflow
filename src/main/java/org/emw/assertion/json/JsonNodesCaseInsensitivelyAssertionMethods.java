package org.emw.assertion.json;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emw.assertion.AssertionGroup;
import org.emw.assertion.collection.CollectionAssertionMethods;
import org.json.JSONArray;

import java.util.Collection;
import java.util.List;

public class JsonNodesCaseInsensitivelyAssertionMethods extends JsonNodesAssertionMethods {
    public final JsonNodesAnyOrderOnlyAssertionMethods not;
    public final JsonNodesNotOnlyAssertionMethods inAnyOrder;

    JsonNodesCaseInsensitivelyAssertionMethods(@Nullable JSONArray actual, boolean negated, boolean ignoreCase, boolean anyOrder, @NonNull List<String> excludedNodes) {
        super(actual, negated, ignoreCase, anyOrder, excludedNodes);
        this.not = new JsonNodesAnyOrderOnlyAssertionMethods(actual, !negated, ignoreCase, anyOrder, excludedNodes);
        this.inAnyOrder = new JsonNodesNotOnlyAssertionMethods(actual, negated, ignoreCase, true, excludedNodes);
    }

    public JsonNodesCaseInsensitivelyAssertionMethods excluding(@NonNull String jsonPointer) {
        this.excludedNodes.add(jsonPointer);
        return this;
    }

}
