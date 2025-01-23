package com.fugro.osa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

	private long locationId;
    private String name;
    
    public Location() {
    	
    }
    
    public Location(long locationId, String name) {
        this.locationId = locationId;
        this.name = name;
    }
    
    // Getters and Setters
    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", name='" + name + '\'' +
                '}';
    }
}
