package com.sample.interaction.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class CompatibleAssembliesTO {
	@JsonProperty("AssyPN")
	String assyPN;
}
