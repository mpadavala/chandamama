package com.sample.will.dto;

public class SubSector extends Sector{

	private String subSector;

	public String getSubSector() {
		return subSector;
	}

	public void setSubSector(String subSector) {
		this.subSector = subSector;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sector()=");
		builder.append(getSector());
		builder.append(", SubSector [subSector=");
		builder.append(subSector);
		builder.append(", Count()=");
		builder.append(getCount());
		builder.append("]");
		return builder.toString();
	}
	
	
}
