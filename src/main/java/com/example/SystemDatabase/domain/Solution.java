package com.example.SystemDatabase.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Solution {

	public Solution() {
		super();
	}
	public Solution(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name, description;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="solutionId")
    private List<Defect> def;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return description;
	}
	public void setDesc(String description) {
		this.description = description;
	}
	public List<Defect> getDef() {
		return def;
	}
	public void setDef(List<Defect> def) {
		this.def = def;
	}
}
