package com.laidu.bishe.utils.annotation;

import com.alibaba.fastjson.serializer.BeforeFilter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenwen on 16/11/18.
 */
@NoArgsConstructor
public class JSONPropertyFilter extends BeforeFilter {
    private Map<String,Boolean> includesMap = new HashMap<String, Boolean>(){{put("default",true);}};

    private IPropertyFilter propertyFilter;

    private Map<String,Boolean> cachedFieldMap = new HashMap<>();

    public JSONPropertyFilter(String[] groupNames){
        for(String group : groupNames){
            includesMap.put(group,true);
        }
    }

    public JSONPropertyFilter(IPropertyFilter propertyFilter){
        this.propertyFilter = propertyFilter;
    }

    @Override
    public void writeBefore(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        for(Field field : fields){
            if (field.isAnnotationPresent(JSONGroup.class)) {
                JSONGroup annotation = field.getAnnotation(JSONGroup.class);
                if (hasField(annotation.value())){
                    continue;
                }
            }else if (hasField(new String[]{"default"})){
                continue;
            }
            try {
                field.setAccessible(true);
                field.set(o,null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
         }
    }

    /**
     * 是否该字段需要序列化
     * @param groups 分组标签
     * @return
     */
    private Boolean hasField(String[] groups){
        for(String group : groups){
            if (cachedFieldMap.containsKey(group)){
                if (cachedFieldMap.get(group)){
                    return true;
                }
                continue;
            }

            if (includesMap.containsKey(group)){
                cachedFieldMap.put(group,true);
                return true;
            }

            if (propertyFilter != null){
                if (propertyFilter.hasGroup(group)){
                    cachedFieldMap.put(group,true);
                    return true;
                }
            }
            cachedFieldMap.put(group,false);
        }
        return false;
    }
}
