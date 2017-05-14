package com.laidu.bishe.utils.util;

import org.apache.commons.lang.StringUtils;

import java.util.Random;

/**
 * * @author hui.sun
 *
 * @email hui.sun@qunar.com
 * @date 2015-10-22
 */
public class PassengerUtil {
    private static Random random = new Random();
    private static String firstName = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元顾孟黄穆萧尹姚邵汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯管卢莫经房裘干解应宗宣丁邓郁单杭洪包诸左石崔龚程邢裴陆荣翁荀羊於惠甄魏封井段富巫乌焦牧车侯全武符刘姜詹束龙叶印宿白蒙池乔阴闻姬申扶堵冉宰牛寿通边扈燕尚农温别庄晏柴瞿阎充慕连习艾鱼容向古易慎戈廖庚终衡都耿满弘匡国文寇广利蔚越师巩厍聂晁冷那简饶曾关相查江红游竺权益桓公万佟";
    private static String girl = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
    private static String boy = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    /**
     * 随机生成成年人身份证号码
     *
     * @return 身份证号
     */
    public static String generateIDCardNo() {
        // 18位身份证号码各位的含义:
        // 1-2位省、自治区、直辖市代码；
        // 3-4位地级市、盟、自治州代码；
        // 5-6位县、县级市、区代码；
        // 7-14位出生年月日，比如19670401代表1967年4月1日；
        // 15-17位为顺序号，其中17位（倒数第二位）男为单数，女为双数；
        // 18位为校验码，0-9和X。
        // 作为尾号的校验码，是由把前十七位数字带入统一的公式计算出来的，
        // 计算的结果是0-10，如果某人的尾号是0－9，都不会出现X，但如果尾号是10，那么就得用X来代替，
        // 因为如果用10做尾号，那么此人的身份证就变成了19位。X是罗马数字的10，用X来代替10
        String id = "";
        // 随机生成省、自治区、直辖市代码 1-2
        String provinces[] = {"11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37",
                "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71",
                "81", "82"};
        String province = provinces[random.nextInt(provinces.length - 1)];
        // 随机生成地级市、盟、自治州代码 3-4
        String citys[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "21", "22", "23", "24", "25",
                "26", "27", "28"};
        String city = citys[random.nextInt(citys.length - 1)];
        // 随机生成县、县级市、区代码 5-6
        String countys[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "21", "22", "23", "24", "25",
                "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38"};
        String county = countys[random.nextInt(countys.length - 1)];
        // 随机生成成年人出生年月
        String birth = generateAdultBirthString();
        // 随机生成顺序号 15-17
        String no = random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9);
        // 随机生成校验码 18
        String checks[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "X"};
        String check = checks[random.nextInt(checks.length - 1)];
        // 拼接身份证号码
        id = province + city + county + birth + no + check;
        return id;
    }

    /**
     * 生成成人出生年月
     *
     * @return 出生年月
     */
    private static String generateAdultBirthString() {
        // 随机生成出生年月\,要求成年，大于18,小于60,出生年 60—96
        String year = String.valueOf(1960 + random.nextInt(36));
        // 随机生成出生月
        String month = parseBirthNumber2String(1 + random.nextInt(12));
        // 随机生成出生日
        String day = parseBirthNumber2String(1 + random.nextInt(28));
        return year + month + day;
    }

    private static String parseBirthNumber2String(int num) {
        if (num < 10) {
            return "0" + String.valueOf(num);
        }
        return String.valueOf(num);
    }

    /**
     * 返回手机号码
     */
    public static String generateTelNo() {
        int index = random.nextInt(telFirst.length);
        String first = telFirst[index];
        String second = String.valueOf(random.nextInt(888) + 10001).substring(1);
        String thrid = String.valueOf(random.nextInt(9101) + 10001).substring(1);
        return first + second + thrid;
    }

    /**
     * 返回中文姓名
     */
    public static String generateChineseName() {
        int index = random.nextInt(firstName.length());
        String first = firstName.substring(index, index + 1);
        int sex = random.nextInt(2);
        String str = boy;
        int length = boy.length();
        if (sex == 0) {
            str = girl;
            length = girl.length();
        }
        index = random.nextInt(length);
        String second = str.substring(index, index + 1);
        int hasThird = random.nextInt(2);
        String third = "";
        if (hasThird == 1) {
            index = random.nextInt(length);
            third = str.substring(index, index + 1);
        }
        return first + second + third;
    }
}
