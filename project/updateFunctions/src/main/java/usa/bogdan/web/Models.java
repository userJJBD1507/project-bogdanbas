package usa.bogdan.web;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "currentTask"
})
public class Models {

    @JsonProperty("currentTask")
    private String currentTask;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("currentTask")
    public String getCurrentTask() {
        return currentTask;
    }

    @JsonProperty("currentTask")
    public void setCurrentTask(String currentTask) {
        this.currentTask = currentTask;
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