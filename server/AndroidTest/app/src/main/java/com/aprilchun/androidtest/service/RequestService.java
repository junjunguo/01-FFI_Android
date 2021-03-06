package com.aprilchun.androidtest.service;

/**
 * Created by chun on 3/4/15.
 */
public interface RequestService {
    public String getAllTeamLocations(String username, String deviceId);

    public String getLatestTeamLocations(String username, String deviceId);

    public String getPeriodTeamLocations(String username, String deviceId, String startTime, String endTime);

    public String getAllTeamTextReports(String username, String deviceId);

    public String getLatestTeamTextReports(String username, String deviceId);

    public String getPeriodTeamTextReports(String username, String deviceId, String startTime, String endTime);

    public String getAllTeamPhotoReports(String username, String deviceId);

    public String getLatestTeamPhotoReports(String username, String deviceId);

    public String getPeriodTeamPhotoReports(String username, String deviceId, String startTime, String endTime);

    public String getPhoto(String username, String deviceId, String picId);
}
