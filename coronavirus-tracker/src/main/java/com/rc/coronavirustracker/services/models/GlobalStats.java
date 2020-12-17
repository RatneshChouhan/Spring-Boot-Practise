package com.rc.coronavirustracker.services.models;

public class GlobalStats {
    private String state;
    private String country;
    long latestTotalCases;
    long diffFrmPrvDay;

    public long getDiffFrmPrvDay() {
        return diffFrmPrvDay;
    }

    public void setDiffFrmPrvDay(long diffFrmPrvDay) {
        this.diffFrmPrvDay = diffFrmPrvDay;
    }

    @Override
    public String toString() {
        return "GlobalStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                '}';
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(long latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }
}