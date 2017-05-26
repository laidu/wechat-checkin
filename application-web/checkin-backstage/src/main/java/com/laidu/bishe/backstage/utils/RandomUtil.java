package com.laidu.bishe.backstage.utils;

import com.laidu.bishe.backstage.enums.FaceActionEnum;
import com.laidu.bishe.backstage.enums.FeatureTypeEnum;
import com.laidu.bishe.backstage.enums.VoiceTextEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by laidu on 2017/5/24.
 */
public class RandomUtil {

    private static int RANDOM=1000;

    private static Random random = new Random(RANDOM);


    /**
     * 获取随机数组
     * @param size 目标数组大小
     * @return 随机数组
     */
    public static <E> List<E> getRandomRes(List<E> list,int size){

        if (list == null && size > list.size()) {
            return null;
        }
        List<E> result = new ArrayList<>();

        if (list.size()==size|| list.size()==1){
            return list;
        }

        for (int i = 0; i < size ; i++) {
            int randomIndex = random.nextInt(list.size() - 1 - i);
            E randomRes = list.get(randomIndex);
            result.add(randomRes);
            E temp = list.get(randomIndex);

            list.add(randomIndex, list.get(list.size()-1-i));

            list.add(list.size()-1-i,temp);

        }
        return result;
    }


    /**
     * 从一定中指定数量的随机数
     * @param sum
     * @param sub
     * @return
     */
    public static List<Integer> randomInt(int sum, int sub){

        List<Integer> subs = new ArrayList<>();


        for (int i=0; i<sub; i++){
            subs.add(random.nextInt(sum));
        }

        return subs;
    }

    public static int randomInt(int sum) {

        List<Integer> integers = randomInt(sum, 1);

        return integers.get(0);
    }

    public static FeatureTypeEnum randomFeatureType() {

        FeatureTypeEnum[] checkinTypes = FeatureTypeEnum.values();

        int random = randomInt(checkinTypes.length);

        return checkinTypes[random];
    }

    public static FaceActionEnum randomFaceAction() {

        FaceActionEnum[] faceActions = FaceActionEnum.values();
        int random = randomInt(faceActions.length);
        return faceActions[random];
    }

    public static VoiceTextEnum randomVoiceText() {

        VoiceTextEnum[] voiceTexts = VoiceTextEnum.values();
        int random = randomInt(voiceTexts.length);
        return voiceTexts[random];
    }
}
