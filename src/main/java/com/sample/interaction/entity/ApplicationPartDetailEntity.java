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
@Table(name = "APPLICATIONPARTDETAIL")
@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationPartDetailEntity implements Serializable{
	
	@Id
	@Column(name= "SUBTYPE" ,insertable = false, updatable = false)
	String subType;
	@Column(name= "FILEPN" , insertable = false, updatable = false)
	String filePN;

	@Column(name= "FROZENSTATUS")
	String frozenStatus;

	@Column(name= "SUCCESSORPARTNUMBER")
	String successorPartNumber;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "FILEPN", insertable = false, updatable = false)
	MGMFileEntity mGMFile;

	@Column(name= "VERSION")
	String version;

	@Column(name= "RUNTIMESIZE")
	String RunTimeSize;
	
}
