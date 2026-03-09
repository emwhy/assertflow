package org.emw.assertion.json;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

public class JsonNodeCaseInsensitivelyAssertionMethods extends JsonNodeAssertionMethods {
    public final JsonNodeAssertionMethods not;

    protected JsonNodeCaseInsensitivelyAssertionMethods(@Nullable Object obj, boolean negated, boolean ignoreCase, List<String> excludedNodes) {
        super(obj, negated, ignoreCase, excludedNodes);
        this.not =  new JsonNodeAssertionMethods(obj, !negated, ignoreCase, excludedNodes);
    }

    public JsonNodeCaseInsensitivelyAssertionMethods excluding(@NonNull String jsonPointer) {
        this.excludedNodes.add(jsonPointer);
        return this;
    }

}
