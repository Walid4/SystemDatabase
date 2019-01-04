package com.example.SystemDatabase.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Defect {

	public Defect() {
		super();
	}
	public Defect(String severity, String errorCode, Solution solutionId) {
		super();
		this.severity = severity;
		this.errorCode = errorCode;
		this.solutionId = solutionId;
	}
	public Defect(String severity, String errorCode, String dSol) {
		super();
		this.severity = severity;
		this.errorCode = errorCode;
		this.setdSol(dSol);
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String severity, errorCode, dSol;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="solutionId")
	private Solution solutionId;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="deffectId")
    private List<DefectInstance> def;
	
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public Solution getSolutionId() {
		return solutionId;
	}
	public void setSolutionId(Solution solutionId) {
		this.solutionId = solutionId;
	}
	public List<DefectInstance> getDef() {
		return def;
	}
	public void setDef(List<DefectInstance> def) {
		this.def = def;
	}
	public String getdSol() {
		return dSol;
	}
	public void setdSol(String dSol) {
		this.dSol = dSol;
	}
}
