package com.sample.interaction.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ApplicationDetailTO {
    @JsonProperty("SubType")
	String subType;
    @JsonProperty("Values")
	List<String> values;
	@JsonProperty("ApplicationPartDetail")
	ApplicationPartDetailTO applicationPartDetail;
	@JsonProperty("ApplicationDescription")
	String applicationDescription;
	@JsonProperty("Serviceable")
	String serviceable;
	@JsonProperty("Testable")
	String testable;

	@JsonProperty("CompatibleAssemblies")
	List<CompatibleAssembliesTO> compatibleAssemblies;

	@JsonProperty("FunctionalTypeValues")
	FunctionalTypeValuesTO functionalTypeValues;

	@JsonProperty("Sellable")
	String sellable;

	@JsonProperty("ResidentOnVehicle")
	String residentOnVehicle;

	@JsonProperty("DigitallySigned")
	String digitallySigned;
	@JsonProperty("ConsumerViewable")
	String consumerViewable;
	@JsonProperty("ConsumerDownloadable")
	String consumerDownloadable;
	@JsonProperty("DealerDownloadable")
	String dealerDownloadable;
	String oTAViewable;
	String oTADownloadable;
}
