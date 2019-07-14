package com.sample.interaction.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "COMPATIBLEASSEMBLIES")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class CompatibleAssembliesEntity implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "COMPATIBLE_ID")
	long compatibleId;

	@Column(name = "ASSYPN")
	String assyPN;

	@Column(name = "SUBTYPE")
	String subType;


}
