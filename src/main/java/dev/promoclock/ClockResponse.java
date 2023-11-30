package dev.promoclock;

public class ClockResponse {

    private String currentDateTime;
    private String expectedDateTime;
    private long years;
    private long months;
    private long days;
    private long hours;
    private long minutes;
    private long seconds;

    public ClockResponse() {

    }

    public ClockResponse(String currentDateTime, String expectedDateTime, long years, long months, long days, long hours, long minutes, long seconds) {
        this.currentDateTime = currentDateTime;
        this.expectedDateTime = expectedDateTime;
        this.years = years;
        this.months = months;
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public String getExpectedDateTime() {
        return expectedDateTime;
    }

    public void setExpectedDateTime(String expectedDateTime) {
        this.expectedDateTime = expectedDateTime;
    }

    public long getYears() {
        return years;
    }

    public void setYears(long years) {
        this.years = years;
    }

    public long getMonths() {
        return months;
    }

    public void setMonths(long months) {
        this.months = months;
    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        this.days = days;
    }

    public long getHours() {
        return hours;
    }

    public void setHours(long hours) {
        this.hours = hours;
    }

    public long getMinutes() {
        return minutes;
    }

    public void setMinutes(long minutes) {
        this.minutes = minutes;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }
}
