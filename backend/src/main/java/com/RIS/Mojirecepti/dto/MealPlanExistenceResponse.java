package com.RIS.Mojirecepti.dto;

public class MealPlanExistenceResponse {
    private boolean exists;

    public MealPlanExistenceResponse(boolean exists) {
        this.exists = exists;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
}