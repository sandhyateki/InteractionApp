package com.sample.interaction.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ProtocolPK implements Serializable{
	@Column(name = "FILEPN" ,insertable = false, updatable = false)
	String filePN;
	@Column(name = "NETWORKPROTOCOL", insertable = false, updatable = false)
	String networkProtocol;

}
