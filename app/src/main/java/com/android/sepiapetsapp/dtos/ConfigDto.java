package com.android.sepiapetsapp.dtos;


public class ConfigDto {

    private Settings settings;

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public class Settings {
        private String workHours;

        public String getWorkHours() {
            return workHours;
        }

        public void setWorkHours(String workHours) {
            this.workHours = workHours;
        }
    }
}
