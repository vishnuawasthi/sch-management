package com.sch.mngt.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class AbstractEntity {

	private Date createdDate;

	private Date updatedDate;

	private String createdBy;
	private String updatedBy;

}
