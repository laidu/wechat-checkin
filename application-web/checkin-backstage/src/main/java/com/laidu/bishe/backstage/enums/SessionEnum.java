package com.laidu.bishe.backstage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 一天内的节次数
 * Created by laidu on 2017/5/21.
 */
@AllArgsConstructor
public enum  SessionEnum {

    Session_1("第一节"),
    Session_2("第二节"),
    Session_3("第三节"),
    Session_4("第四节"),
    Session_5("第五节"),
    Session_6("第六节"),
    Session_7("第七节"),
    Session_8("第八节"),
    Session_9("第九节"),
    Session_10("第十节"),
    Session_11("第十一节"),
    Session_13("第十二节"),
    ;
    /**
     * 一天内的节次数
     */
    @Getter@Setter
    public String name;
}
