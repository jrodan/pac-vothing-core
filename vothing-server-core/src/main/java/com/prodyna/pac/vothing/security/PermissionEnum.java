package com.prodyna.pac.vothing.security;

public enum PermissionEnum {
	
	// TODO create permission mechanism where any entity implements some permission interfaces
	
	NONE("GUEST"),
	
	SURVEY_LIST("SURVEY_LIST"),
	
	SURVEY_ADD("SURVEY_ADD"), 
	
	SURVEY_UPDATE("SURVEY_UPDATE"), 
	
	SURVEY_DELETE("SURVEY_DELETE");
	
	private String name = "";
	
	PermissionEnum(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
	
	
}
