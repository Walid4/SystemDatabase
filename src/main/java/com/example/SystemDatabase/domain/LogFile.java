package com.example.SystemDatabase.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class LogFile {
	public LogFile() {
		super();
	}

	public LogFile(String name, Date date) {
		super();
		this.name = name;
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private Date date;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="logId")
    private List<DefectInstance> def;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<DefectInstance> getDef() {
		return def;
	}

	public void setDef(List<DefectInstance> def) {
		this.def = def;
	}
}
