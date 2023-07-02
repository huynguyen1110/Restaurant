package com.hivetech.ine2.htine2.service;

import com.hivetech.ine2.htine2.model.storage.entity.EAction;
import com.hivetech.ine2.htine2.model.storage.entity.LogInfo;
import com.hivetech.ine2.htine2.repository.LogInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
@Slf4j
public class GetLogInfoService {

    @Autowired
    private LogInfoRepository logInfoRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    private static String username;


    public void getLogInInfo(String usernameInput) {
        LogInfo logInfo = new LogInfo();
        username = usernameInput;
        logInfo.setUsername(username);
        logInfo.setAction(EAction.LOGIN.toString());
        logInfo.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        logInfo.setIpAddress(httpServletRequest.getRemoteAddr());
        logInfo.setUserAgent(httpServletRequest.getHeader("User-Agent"));
        logInfoRepository.save(logInfo);
    }

    public void getLogOutInfo(String usernameInput) {
        LogInfo logInfo = new LogInfo();
        username = usernameInput;
        logInfo.setUsername(username);
        logInfo.setAction(EAction.LOGOUT.toString());
        logInfo.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        logInfo.setIpAddress(httpServletRequest.getRemoteAddr());
        logInfo.setUserAgent(httpServletRequest.getHeader("User-Agent"));
        logInfoRepository.save(logInfo);
        SecurityContextHolder.clearContext();
    }

    public List<LogInfo> getAllLogInfo() {
        List<LogInfo> logInformation = logInfoRepository.findAll();
        return logInformation;
    }

    public String fetchData(Model model) {
        Date currentTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-d hh:mm:ss a z");

        List<LogInfo> listInfo = getAllLogInfo();
        TimeZone timestamp = null;
        for (LogInfo infoData: listInfo) {
            timestamp = infoData.getTimeZone();
            simpleDateFormat.setTimeZone(timestamp);
        }
        model.addAttribute("logInfo", new LogInfo());
        model.addAttribute("listInfo", listInfo);
        model.addAttribute("timestamp", simpleDateFormat.format(currentTime));
        return "login_table";
    }
}
