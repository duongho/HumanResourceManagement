package com.ksu.grad.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Attribute generated by hbm2java
 */
@Entity
@Table(name = "Attribute", catalog = "EMAS", uniqueConstraints = @UniqueConstraint(columnNames = "Label"))
public class Attribute implements java.io.Serializable {

	private Integer id;
	private String label;
	private String description;
	private String method;
	private boolean isNotification;
	private Set<AttributeStatus> attributeStatuses = new HashSet<AttributeStatus>(0);

	public Attribute() {
	}

	public Attribute(String label, String method, boolean isNotification) {
		this.label = label;
		this.method = method;
		this.isNotification = isNotification;
	}

	public Attribute(String label, String description, String method, boolean isNotification,
			Set<AttributeStatus> attributeStatuses) {
		this.label = label;
		this.description = description;
		this.method = method;
		this.isNotification = isNotification;
		this.attributeStatuses = attributeStatuses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Label", unique = true, nullable = false, length = 45)
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "Description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "Method", nullable = false)
	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Column(name = "IsNotification", nullable = false)
	public boolean isIsNotification() {
		return this.isNotification;
	}

	public void setIsNotification(boolean isNotification) {
		this.isNotification = isNotification;
	}

	@JsonBackReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "attribute")
	public Set<AttributeStatus> getAttributeStatuses() {
		return this.attributeStatuses;
	}

	public void setAttributeStatuses(Set<AttributeStatus> attributeStatuses) {
		this.attributeStatuses = attributeStatuses;
	}

}
