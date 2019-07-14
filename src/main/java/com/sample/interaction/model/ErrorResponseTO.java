package com.sample.interaction.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ErrorResponseTO {

        @JsonProperty("errors")
        private List<ErrorTO> errors;
        @JsonProperty("error")
        private ErrorTO error;

}

