package org.emw.assertion.json;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

public class JsonNodeNotAssertionMethods extends JsonNodeAssertionMethods {
    public final JsonNodeAssertionMethods caseInsensitively;

    protected JsonNodeNotAssertionMethods(@Nullable Object obj, boolean negated, boolean ignoreCase, List<String> excludedNodes) {
        super(obj, negated, ignoreCase, excludedNodes);
        this.caseInsensitively = new JsonNodeAssertionMethods(obj, negated, true, excludedNodes);
    }

    public JsonNodeNotAssertionMethods excluding(@NonNull String jsonPointer) {
        this.excludedNodes.add(jsonPointer);
        return this;
    }
}
