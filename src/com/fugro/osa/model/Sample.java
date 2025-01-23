package com.fugro.osa.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sample {

	private long sampleId;
    private Location location;
    private LocalDate dateCollected;
    private double unitWeight;  // in kN/m3
    private double waterContent; // percentage
    private double shearStrength; // in kPa

    public Sample() {
    	
    }
    
//    public Sample(long sampleId, Location location, LocalDate dateCollected, double unitWeight, double waterContent, double shearStrength) {
//        this.sampleId = sampleId;
//        this.location = location;
//        this.dateCollected = dateCollected;
//        this.unitWeight = unitWeight;
//        this.waterContent = waterContent;
//        this.shearStrength = shearStrength;
//    }
//    
    // Getters and Setters
    public long getSampleId() {
        return sampleId;
    }

    public void setId(long sampleId) {
        this.sampleId = sampleId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDate getDateCollected() {
        return dateCollected;
    }

    public void setDateCollected(LocalDate dateCollected) {
        this.dateCollected = dateCollected;
    }

    public double getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(double unitWeight) {
        this.unitWeight = unitWeight;
    }

    public double getWaterContent() {
        return waterContent;
    }

    public void setWaterContent(double waterContent) {
        this.waterContent = waterContent;
    }

    public double getShearStrength() {
        return shearStrength;
    }

    public void setShearStrength(double shearStrength) {
        this.shearStrength = shearStrength;
    }

    @Override
    public String toString() {
        return "Sample{sampleId='" + sampleId + "', location='" + location + "', dateCollected=" + dateCollected
                + ", unitWeight=" + unitWeight + ", waterContent=" + waterContent + ", shearStrength=" + shearStrength + "}";
    }
    
    public boolean isValid() {
        return waterContent > 5 && waterContent < 150 && unitWeight > 12 && unitWeight < 26 && shearStrength > 2 && shearStrength < 1000;
    }
}
