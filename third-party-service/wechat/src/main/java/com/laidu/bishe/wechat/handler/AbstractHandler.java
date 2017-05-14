package com.laidu.bishe.wechat.handler;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Binary Wang
 *
 */
@Slf4j
public abstract class AbstractHandler implements WxMpMessageHandler {
    protected Logger logger = log;
}
