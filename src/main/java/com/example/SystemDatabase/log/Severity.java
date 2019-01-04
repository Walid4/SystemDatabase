package com.example.SystemDatabase.log;

public class Severity {
	
	private SeverityLevel s;
	private int criticalCnt = 0, errorCnt = 0, warningCnt = 0;
	
	public SeverityLevel getS() {
		return s;
	}
	public void setS(SeverityLevel s) {
		this.s = s;
	}
	public int getCriticalCnt() {
		return criticalCnt;
	}
	public void setCriticalCnt(int criticalCnt) {
		this.criticalCnt = criticalCnt;
	}
	public int getErrorCnt() {
		return errorCnt;
	}
	public void setErrorCnt(int errorCnt) {
		this.errorCnt = errorCnt;
	}
	public int getWarningCnt() {
		return warningCnt;
	}
	public void setWarningCnt(int warningCnt) {
		this.warningCnt = warningCnt;
	}
	
}
