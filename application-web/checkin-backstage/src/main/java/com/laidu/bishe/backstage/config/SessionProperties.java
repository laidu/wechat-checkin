package com.laidu.bishe.backstage.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by laidu on 2017/5/21.
 */
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class SessionProperties {

    @Value("${session.before_min:15}")
    private int beforeMin;
}
