package com.ksu.grad.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * AttributeStatus generated by hbm2java
 */
@Entity
@Table(name = "AttributeStatus", catalog = "EMAS", uniqueConstraints = @UniqueConstraint(columnNames = { "AttributeId",
		"StatusId" }))
public class AttributeStatus implements java.io.Serializable {

	private Integer id;
	private Attribute attribute;
	private Status status;
	private boolean isFinal;
	private Set<EmployeeHistory> employeeHistories = new HashSet<EmployeeHistory>(0);

	public AttributeStatus() {
	}

	public AttributeStatus(Attribute attribute, Status status, boolean isFinal) {
		this.attribute = attribute;
		this.status = status;
		this.isFinal = isFinal;
	}

	public AttributeStatus(Attribute attribute, Status status, boolean isFinal,
			Set<EmployeeHistory> employeeHistories) {
		this.attribute = attribute;
		this.status = status;
		this.isFinal = isFinal;
		this.employeeHistories = employeeHistories;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AttributeId", nullable = false)
	public Attribute getAttribute() {
		return this.attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StatusId", nullable = false)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name = "IsFinal", nullable = false)
	public boolean isIsFinal() {
		return this.isFinal;
	}

	public void setIsFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "attributeStatus")
	public Set<EmployeeHistory> getEmployeeHistories() {
		return this.employeeHistories;
	}

	public void setEmployeeHistories(Set<EmployeeHistory> employeeHistories) {
		this.employeeHistories = employeeHistories;
	}

}
