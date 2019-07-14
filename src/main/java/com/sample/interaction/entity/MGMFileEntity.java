package com.sample.interaction.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "MGMFILE")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class MGMFileEntity implements Serializable{
	
	@Id
	@Column(name = "FILEPN",insertable = false, updatable = false)
	String filePN;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "FILEPN")
	Set<ProtocolEntity> protocol;

	@Column(name = "FILEFORMAT")
	String fileFormat;

	@Column(name = "CHECKSUM")
	String checksum;

	@Column(name = "FILEFINGERPRINT")
	String fileFingerprint;

	@Column(name = "ROMSIZEKB")
	String rOMSizeKB;

	@Column(name = "FILESIZE")
	String size;

	@Column(name = "FILEDATEMODIFIED")
	Timestamp fileDateModified;
	
}
