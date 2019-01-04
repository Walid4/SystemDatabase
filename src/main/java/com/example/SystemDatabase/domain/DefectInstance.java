package com.example.SystemDatabase.domain;

import javax.persistence.*;

@Entity
public class DefectInstance {

	public DefectInstance() {
		super();
	}

	public DefectInstance(LogFile logId, Application appId, Defect deffectId) {
		super();
		this.logId = logId;
		this.appId = appId;
		this.deffectId = deffectId;
		
	}
	public DefectInstance(Application appId, Defect deffectId) {
		super();
		this.appId = appId;
		this.deffectId = deffectId;
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="logId")
	private LogFile logId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="appId")
	private Application appId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="deffectId")
	private Defect deffectId;

	
	public LogFile getLogId() {
		return logId;
	}

	public void setLogId(LogFile logId) {
		this.logId = logId;
	}
	
	public Application getAppId() {
		return appId;
	}

	public void setAppId(Application appId) {
		this.appId = appId;
	}

	public Defect getDeffectId() {
		return deffectId;
	}

	public void setDeffectId(Defect deffectId) {
		this.deffectId = deffectId;
	}
}
