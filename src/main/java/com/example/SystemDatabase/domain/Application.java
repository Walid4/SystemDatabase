package com.example.SystemDatabase.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Application {
	
	public Application() {
		super();
	}
	public Application(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name, type;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="appId")
    private List<DefectInstance> defs;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<DefectInstance> getDefs() {
		return defs;
	}
	public void setDefs(List<DefectInstance> defs) {
		this.defs = defs;
	}
	

}
