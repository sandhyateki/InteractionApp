package com.sample.interaction.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ApplicationPartDetailTO {

	@JsonProperty("FilePN")
	String filePN;
	@JsonProperty("FrozenStatus")
	String frozenStatus;
	@JsonProperty("SuccessorPartNumber")
	String successorPartNumber;
	MGMFileTO mGMFile;
	@JsonProperty("Version")
	String version;
	@JsonProperty("RunTimeSize")
	String runTimeSize;
}
