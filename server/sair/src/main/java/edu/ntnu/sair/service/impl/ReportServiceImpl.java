package edu.ntnu.sair.service.impl;

import edu.ntnu.sair.dao.LocationDao;
import edu.ntnu.sair.dao.MemberDao;
import edu.ntnu.sair.dao.PhotoReportDao;
import edu.ntnu.sair.dao.TextReportDao;
import edu.ntnu.sair.model.Location;
import edu.ntnu.sair.model.PhotoReport;
import edu.ntnu.sair.model.Result;
import edu.ntnu.sair.model.TextReport;
import edu.ntnu.sair.service.ReportService;
import edu.ntnu.sair.service.UserService;
import edu.ntnu.sair.util.Coder;
import edu.ntnu.sair.util.Constant;
import org.apache.cxf.annotations.GZIP;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;
import java.io.File;
import java.util.Calendar;

/**
 * Created by chun on 2/16/15.
 */

@GZIP(force = true, threshold = 0)
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
@WebService(endpointInterface = "edu.ntnu.sair.service.ReportService", targetNamespace = "http://service.sair.ntnu.edu/")
@Service("reportService")
public class ReportServiceImpl implements ReportService {
    private UserService userService;
    private MemberDao memberDao;
    private LocationDao locationDao;
    private TextReportDao textReportDao;
    private PhotoReportDao photoReportDao;


    @Transactional
    @Override
    public String sendLocationReport(String username, String uuid, String sendingTime, String latitude, String longitude) {
        String checkLogin = this.userService.checkLogin(username, uuid);
        if (!checkLogin.equals("success")) {
            return checkLogin;
        }
        Location location = new Location();
        location.setMember(this.memberDao.getByUsername(username));
        location.setLongitude(Double.valueOf(longitude));
        location.setLatitude(Double.valueOf(latitude));
        Calendar calendar = Calendar.getInstance();
        location.setServerTimestamp(calendar);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(Long.valueOf(sendingTime));
        location.setClientTimestamp(calendar2);
        this.locationDao.add(location);
        return new Result("sendLocationReport", "success").toString();
    }

    @Transactional
    @Override
    public String sendLocationReportList(String username, String uuid, String sendingTime, String list) {
        String checkLogin = this.userService.checkLogin(username, uuid);
        if (!checkLogin.equals("success")) {
            return checkLogin;
        }
        try {
            JSONArray array = new JSONArray(list);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Location location = new Location();
                location.setMember(this.memberDao.getByUsername((String) object.get("username")));
                location.setLongitude(Double.valueOf((String) object.get("longitude")));
                location.setLatitude(Double.valueOf((String) object.get("latitude")));
                Calendar calendar = Calendar.getInstance();
                location.setServerTimestamp(calendar);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTimeInMillis(Long.valueOf((String) object.get("sendingTime")));
                location.setClientTimestamp(calendar2);
                this.locationDao.add(location);
            }

            return new Result("sendLocationReportList", "success").toString();
        } catch (Exception e) {
            return new Result("sendLocationReportList", "server error").toString();
        }


    }

    @Transactional
    @Override
    public String sendTextReport(String username, String uuid, String sendingTime, String latitude, String longitude, String content) {
        String checkLogin = this.userService.checkLogin(username, uuid);
        if (!checkLogin.equals("success")) {
            return checkLogin;
        }
        Location location = new Location();
        location.setMember(this.memberDao.getByUsername(username));
        location.setLongitude(Double.valueOf(longitude));
        location.setLatitude(Double.valueOf(latitude));
        Calendar calendar = Calendar.getInstance();
        location.setServerTimestamp(calendar);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(Long.valueOf(sendingTime));
        location.setClientTimestamp(calendar2);
        this.locationDao.add(location);

        TextReport textReport = new TextReport();
        textReport.setLocation(location);
        textReport.setContent(content);
        this.textReportDao.add(textReport);
        return new Result("sendTextReport", "success").toString();
    }

    @Transactional
    @Override
    public String sendTextReportList(String username, String uuid, String sendingTime, String list) {
        String checkLogin = this.userService.checkLogin(username, uuid);
        if (!checkLogin.equals("success")) {
            return checkLogin;
        }
        Location location = new Location();
        location.setMember(this.memberDao.getByUsername(username));
        location.setLongitude(Double.valueOf(longitude));
        location.setLatitude(Double.valueOf(latitude));
        Calendar calendar = Calendar.getInstance();
        location.setServerTimestamp(calendar);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(Long.valueOf(sendingTime));
        location.setClientTimestamp(calendar2);
        this.locationDao.add(location);

        TextReport textReport = new TextReport();
        textReport.setLocation(location);
        textReport.setContent(content);
        this.textReportDao.add(textReport);
        return new Result("sendTextReport", "success").toString();
    }

    @Transactional
    @Override
    public String sendPhotoReport(String username, String uuid, String sendingTime, String latitude, String longitude, String direction, String file, String extension, String description) {
        String checkLogin = this.userService.checkLogin(username, uuid);
        if (!checkLogin.equals("success")) {
            return checkLogin;
        }
        Location location = new Location();
        location.setMember(this.memberDao.getByUsername(username));
        location.setLongitude(Double.valueOf(longitude));
        location.setLatitude(Double.valueOf(latitude));
        Calendar calendar = Calendar.getInstance();
        location.setServerTimestamp(calendar);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(Long.valueOf(sendingTime));
        location.setClientTimestamp(calendar2);
        this.locationDao.add(location);

        PhotoReport photoReport = new PhotoReport();
        photoReport.setLocation(location);
        photoReport.setDirection(Integer.valueOf(direction));
        photoReport.setName(username + location.getClientTimestamp().getTimeInMillis());
        photoReport.setExtension(extension);
        photoReport.setDescription(description);
        this.photoReportDao.add(photoReport);

        File serverFile = new File(Constant.PHOTO_PATH, photoReport.getName() + "." + photoReport.getExtension());
        Coder.decryptBASE64(serverFile, file);
        return new Result("sendPhotoReport", "success").toString();
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Autowired
    public void setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Autowired
    public void setTextReportDao(TextReportDao textReportDao) {
        this.textReportDao = textReportDao;
    }

    @Autowired
    public void setPhotoReportDao(PhotoReportDao photoReportDao) {
        this.photoReportDao = photoReportDao;
    }
}
