package com.btsbetting.domain.odds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "label_id",
        "label_name",
        "values"
})
public class Bet {

    @JsonProperty("label_id")
    private Integer labelId;
    @JsonProperty("label_name")
    private String labelName;
    @JsonProperty("values")
    private List<Values> values = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("label_id")
    public Integer getLabelId() {
        return labelId;
    }

    @JsonProperty("label_id")
    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    @JsonProperty("label_name")
    public String getLabelName() {
        return labelName;
    }

    @JsonProperty("label_name")
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    @JsonProperty("values")
    public List<Values> getValues() {
        return values;
    }

    @JsonProperty("values")
    public void setValues(List<Values> values) {
        this.values = values;
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