package com.btsbetting.domain;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;


@JsonPropertyOrder({
        "api"
})
public class ApiWrapper {

    @JsonProperty("api")
    private Api api;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("api")
    public Api getApi() {
        return api;
    }

    @JsonProperty("api")
    public void setApi(Api api) {
        this.api = api;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
