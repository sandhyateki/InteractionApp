package com.sample.interaction.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class AppDtlsResponseTO {
	@JsonProperty("ApplicationDetail")
	List<ApplicationDetailTO> applicationDetail;
}
