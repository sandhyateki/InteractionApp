package com.sample.interaction.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.Set;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class MGMFileTO {

	@JsonProperty("Protocol")
	private Set<ProtocolTO> protocol;

	@JsonProperty("FileFormat")
	private String fileFormat;
	@JsonProperty("Checksum")
	private String checksum;
	@JsonProperty("FileFingerprint")
	private String fileFingerprint;
//	@JsonProperty("ROMSizeKB")
	private String rOMSizeKB;
	@JsonProperty("Size")
	private String size;
	@JsonProperty("FileDateModified")
	private Timestamp fileDateModified;

}
