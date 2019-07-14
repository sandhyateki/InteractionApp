package com.sample.interaction.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FUNCTIONALTYPEVALUES")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class FunctionalTypeValuesEntity implements Serializable{
	
	@Id
	@Column(name = "SUBTYPE")
	String subType;

	@Column(name = "FUNCTIONALTYPE")
	String functionalType;

}
