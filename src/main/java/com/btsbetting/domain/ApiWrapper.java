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
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public ApiWrapper() {
    }

    /**
     *
     * @param api
     */
    public ApiWrapper(Api api) {
        super();
        this.api = api;
    }

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
