package com.zero.yoga.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by Zero on 2016/7/24.
 */
public class InputUtils {
    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    private final static Pattern phone = Pattern
            .compile("^((13[0-9])|170|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 返回当前系统时间
     */
    public static String getDataTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    /**
     * 返回当前系统时间
     */
    public static String getDataTime() {
        return getDataTime("HH:mm");
    }

    /**
     * 毫秒值转换为mm:ss
     *
     * @param ms
     * @author kymjs
     */
    public static String timeFormat(int ms) {
        StringBuilder time = new StringBuilder();
        time.delete(0, time.length());
        ms /= 1000;
        int s = ms % 60;
        int min = ms / 60;
        if (min < 10) {
            time.append(0);
        }
        time.append(min).append(":");
        if (s < 10) {
            time.append(0);
        }
        time.append(s);
        return time.toString();
    }

    /**
     * 将字符串转位日期类型
     *
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 判断给定字符串时间是否为今日
     *
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate) {
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            String nowDate = dateFormater2.get().format(today);
            String timeDate = dateFormater2.get().format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     */
    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }

    /**
     * 判断是不是一个合法的手机号码
     */
    public static boolean isPhone(String phoneNum) {
        return phoneNum.trim().length() == 11;
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
//        L.i("toInt: " + str);
        int result = defValue;
        try {
            result = Integer.parseInt(str.trim());
        } catch (Exception e) {
//            L.e(e.getMessage());
            try {
                result = (int) Float.parseFloat(str);
            } catch (Exception e1) {
//                L.e(e1.getMessage());
            }
        } finally {
            return result;
        }
    }

//    public static float toFloat(String str, float defValue) {
//        float result = defValue;
//        try {
//            DecimalFormat a = new DecimalFormat(".##");
////            a.f
//            result = Float.parseFloat(str.trim());
//        } catch (Exception e) {
//        } finally {
//            return result;
//        }
//    }

    /**
     * 对象转整
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * String转long
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * String转double
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static double toDouble(String obj) {
        try {
            return Double.parseDouble(obj);
        } catch (Exception e) {
        }
        return 0D;
    }

    /**
     * 字符串转布尔
     *
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 判断一个字符串是不是数字
     */
    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 获取AppKey
     */
    public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String apiKey = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                apiKey = metaData.getString(metaKey);
            }
        } catch (PackageManager.NameNotFoundException e) {

        }
        return apiKey;
    }

    /**
     * 获取手机IMEI码
     */
    public static String getPhoneIMEI(Activity aty) {
        TelephonyManager tm = (TelephonyManager) aty
                .getSystemService(Activity.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * MD5加密
     */
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(
                    string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * KJ加密
     */
    public static String KJencrypt(String str) {
        char[] cstr = str.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char c : cstr) {
            hex.append((char) (c + 5));
        }
        return hex.toString();
    }

    /**
     * KJ解密
     */
    public static String KJdecipher(String str) {
        char[] cstr = str.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char c : cstr) {
            hex.append((char) (c - 5));
        }
        return hex.toString();
    }

    // 判断手机号码是否有效
    public static boolean isValidTelNumber(String telNumber) {
        if (!TextUtils.isEmpty(telNumber)) {
            String regex = "(\\+\\d+)?1[34578]\\d{9}$";
            return Pattern.matches(regex, telNumber);
        }

        return false;
    }

    // 判断邮箱地址是否有效
    public static boolean isValidEmailAddress(String emailAddress) {

        if (!TextUtils.isEmpty(emailAddress)) {
            String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
            return Pattern.matches(regex, emailAddress);
        }

        return false;
    }

    // 判断内容是否由字母，数字，下划线组成
    public static boolean isValidContent(String content) {

        if (!TextUtils.isEmpty(content)) {
            String regex = "^[\\w\\u4e00-\\u9fa5]+$";
            return Pattern.matches(regex, content);
        }

        return false;
    }

    public static boolean isBankCardNumber(String content) {

        if (!TextUtils.isEmpty(content)) {
            String regex = "^\\d{16}|\\d{17}|\\d{18}|\\d{19}$";
            return Pattern.matches(regex, content);
        }
        return false;
    }

    // 判断身份证号码是否有效
    public static boolean isValidIdCard(String idCard) {
        return IdcardValidator.isValidateIdcard(idCard);
    }

    // 校验身份证的基本组成
    public boolean isIdcard(String idCard) {
        if (!TextUtils.isEmpty(idCard)) {
            String regex = "(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)";
            return Pattern.matches(regex, idCard);
        }

        return false;
    }

    // 校验15身份证的基本组成
    public boolean is15Idcard(String idCard) {
        if (!TextUtils.isEmpty(idCard)) {
            String regex = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
            return Pattern.matches(regex, idCard);
        }

        return false;
    }

    // 校验18身份证的基本组成
    public boolean is18Idcard(String idCard) {
        if (!TextUtils.isEmpty(idCard)) {
            String regex = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$";
            return Pattern.matches(regex, idCard);
        }

        return false;
    }

    /**
     * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * <p>
     * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
     * <p>
     * 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。
     * <p>
     * 第十八位数字(校验码)的计算方法为：
     * 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * 2.将这17位数字和系数相乘的结果相加。
     * 3.用加出来和除以11，看余数是多少？
     * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为: 1 0 X 9 8 7 6 5 4 3 2;
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
     */
    private static class IdcardValidator {

        // 省,直辖市代码表
        private static final String codeAndCity[][] = {{"11", "北京"}, {"12", "天津"},
                {"13", "河北"}, {"14", "山西"}, {"15", "内蒙古"}, {"21", "辽宁"},
                {"22", "吉林"}, {"23", "黑龙江"}, {"31", "上海"}, {"32", "江苏"},
                {"33", "浙江"}, {"34", "安徽"}, {"35", "福建"}, {"36", "江西"},
                {"37", "山东"}, {"41", "河南"}, {"42", "湖北"}, {"43", "湖南"},
                {"44", "广东"}, {"45", "广西"}, {"46", "海南"}, {"50", "重庆"},
                {"51", "四川"}, {"52", "贵州"}, {"53", "云南"}, {"54", "西藏"},
                {"61", "陕西"}, {"62", "甘肃"}, {"63", "青海"}, {"64", "宁夏"},
                {"65", "新疆"}, {"71", "台湾"}, {"81", "香港"}, {"82", "澳门"},
                {"91", "国外"}};

        // 每位加权因子
        private static final int power[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

        // 判断18位身份证号码是否有效
        private static boolean isValidate18Idcard(String idcard) {
            if (idcard.length() != 18) {
                return false;
            }
            String idcard17 = idcard.substring(0, 17);
            String idcard18Code = idcard.substring(17, 18);
            char c[];
            String checkCode;
            if (isDigital(idcard17)) {
                c = idcard17.toCharArray();
            } else {
                return false;
            }

            if (null != c) {
                int bit[] = converCharToInt(c);
                int sum17 = getPowerSum(bit);

                // 将和值与11取模得到余数进行校验码判断
                checkCode = getCheckCodeBySum(sum17);
                if (null == checkCode) {
                    return false;
                }
                // 将身份证的第18位与算出来的校码进行匹配，不相等就为假
                if (!idcard18Code.equalsIgnoreCase(checkCode)) {
                    return false;
                }
            }

            return true;
        }

        // 将15位的身份证转成18位身份证
        public static String convertIdcarBy15bit(String idcard) {
            String idcard18 = null;
            if (idcard.length() != 15) {
                return null;
            }

            if (isDigital(idcard)) {
                // 获取出生年月日
                String birthday = idcard.substring(6, 12);
                Date birthdate = null;
                try {
                    birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar cday = Calendar.getInstance();
                cday.setTime(birthdate);
                String year = String.valueOf(cday.get(Calendar.YEAR));

                idcard18 = idcard.substring(0, 6) + year + idcard.substring(8);

                char c[] = idcard18.toCharArray();
                String checkCode = "";

                if (null != c) {
                    int bit[] = converCharToInt(c);
                    int sum17;
                    sum17 = getPowerSum(bit);
                    // 获取和值与11取模得到余数进行校验码
                    checkCode = getCheckCodeBySum(sum17);
                    // 获取不到校验位
                    if (null == checkCode) {
                        return null;
                    }

                    // 将前17位与第18位校验码拼接
                    idcard18 += checkCode;
                }
            } else {
                return null;
            }

            return idcard18;
        }

        // 是否全部由数字组成
        public static boolean isDigital(String str) {
            return str == null || "".equals(str) ? false : str.matches("^[0-9]*$");
        }

        // 将身份证的每位和对应位的加权因子相乘之后，再得到和值
        public static int getPowerSum(int[] bit) {
            int sum = 0;
            if (power.length != bit.length) {
                return sum;
            }

            for (int i = 0; i < bit.length; i++) {
                for (int j = 0; j < power.length; j++) {
                    if (i == j) {
                        sum = sum + bit[i] * power[j];
                    }
                }
            }

            return sum;
        }

        // 将和值与11取模得到余数进行校验码判断
        private static String getCheckCodeBySum(int sum17) {
            String checkCode = null;
            switch (sum17 % 11) {
                case 10:
                    checkCode = "2";
                    break;
                case 9:
                    checkCode = "3";
                    break;
                case 8:
                    checkCode = "4";
                    break;
                case 7:
                    checkCode = "5";
                    break;
                case 6:
                    checkCode = "6";
                    break;
                case 5:
                    checkCode = "7";
                    break;
                case 4:
                    checkCode = "8";
                    break;
                case 3:
                    checkCode = "9";
                    break;
                case 2:
                    checkCode = "x";
                    break;
                case 1:
                    checkCode = "0";
                    break;
                case 0:
                    checkCode = "1";
                    break;
            }

            return checkCode;
        }

        // 将字符数组转为整型数组
        private static int[] converCharToInt(char[] c) throws NumberFormatException {
            int[] a = new int[c.length];
            int k = 0;
            for (char temp : c) {
                a[k++] = Integer.parseInt(String.valueOf(temp));
            }

            return a;
        }

        // 验证身份证号码是否有效
        public static boolean isValidateIdcard(String idcard) {
            if (!TextUtils.isEmpty(idcard)) {
                if (idcard.length() == 15) {
                    return isValidate18Idcard(convertIdcarBy15bit(idcard));
                } else if (idcard.length() == 18) {
                    return isValidate18Idcard(idcard);
                }
            }

            return false;
        }
    }
}
