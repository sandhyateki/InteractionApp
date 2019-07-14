package com.sample.interaction.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "APPLICATION")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ApplicationDetailEntity implements Serializable {
	
	@Id
	@Column(name = "SUBTYPE")
	String subType;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "SUBTYPE" )
	Set<ValuesEntity> valuesEntity;

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "SUBTYPE")
	ApplicationPartDetailEntity applicationPartDetail;

	@Column(name = "APPLICATIONDESCRIPTION")
	String applicationDescription;

	@Column(name = "SERVICEABLE")
	String serviceable;

	@Column(name = "TESTABLE")
	String testable;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "SUBTYPE")
	Set<CompatibleAssembliesEntity> compatibleAssemblies;

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "SUBTYPE")
	FunctionalTypeValuesEntity functionalTypeValues;

	@Column(name = "SELLABLE")
	String sellable;

	@Column(name = "RESIDENTONVEHICLE")
	String residentOnVehicle;

	@Column(name = "DIGITALLYSIGNED")
	String digitallySigned;

	@Column(name = "CONSUMERVIEWABLE")
	String consumerViewable;

	@Column(name = "CONSUMERDOWNLOADABLE")
	String consumerDownloadable;

	@Column(name = "DEALERDOWNLOADABLE")
	String dealerDownloadable;

	@Column(name = "OTAVIEWABLE")
	String oTAViewable;

	@Column(name = "OTADOWNLOADABLE")
	String oTADownloadable;

}
