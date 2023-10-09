package com.cdac.caneadviser.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MobileUserHit {

    @NotNull(message = "Farm ID is required")
    @Pattern(regexp = "\\d+", message = "Farm ID must contain only numbers")
    private String farmId;

    @NotNull(message = "Opened Date is required")
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Invalid Date Format. Use DD-MM-YYYY format")
    private String openedDate;

    @NotNull(message = "Opened Time is required")
    @Pattern(regexp = "\\d{2}:\\d{2}:\\d{2}", message = "Invalid Time Format. Use HH:MM:SS format")
    private String openedTime;

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(String openedDate) {
        this.openedDate = openedDate;
    }

    public String getOpenedTime() {
        return openedTime;
    }

    public void setOpenedTime(String openedTime) {
        this.openedTime = openedTime;
    }
}
