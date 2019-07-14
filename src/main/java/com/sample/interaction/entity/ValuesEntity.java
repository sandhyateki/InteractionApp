package com.sample.interaction.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "VALUES_TABLE")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ValuesEntity implements Serializable{

	@Id
	@GeneratedValue
	@Column(name = "VALUES_ID")
	long valuesId;

	@Column(name = "SUBTYPE")
	String subType;


	@Column(name = "VAL")
	String value;



}
