package com.laidu.bishe.backstage.service.impl;

import com.laidu.bishe.backstage.model.SessionInfo;
import com.laidu.bishe.backstage.service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by laidu on 2017/5/21.
 */
@Slf4j
@Service
public class SessionServiceImpl implements SessionService{



    @Override
    public SessionInfo getSessionInfo(Date current, int beforeMin) {
        return null;
    }
}
