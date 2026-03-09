package org.emw.assertion.json;

import org.json.JSONException;

public interface JsonAssertor {
    default JsonNodeAssertion assertJson(String json) {
        if (JsonHelper.jsonType(json) == JsonType.Object) {
            return new JsonNodeAssertion(json);
        } else {
            throw new JSONException("This is potentially a Json array rather than an object. Use 'assertJsonArray' instead.");
        }
    }

    default JsonNodesAssertion assertJsonArray(String json) {
        if (JsonHelper.jsonType(json) == JsonType.Array) {
            return new JsonNodesAssertion(json);
        } else {
            throw new JSONException("This is potentially a Json object rather than an array. Use 'assertJson' instead.");
        }
    }
}
