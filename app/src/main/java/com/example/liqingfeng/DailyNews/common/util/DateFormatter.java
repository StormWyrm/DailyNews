package com.example.liqingfeng.DailyNews.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/23 10:52
 * @DESC: 将日期转换成20170323的格式
 * @VERSION: V1.0
 */
public class DateFormatter {


    public static String ZhihuDailyNowDateFormat(Calendar now) {
        return new SimpleDateFormat("yyyyMMdd").format(now.getTime());
    }

    public static String DoubanDateFormat(Calendar now) {

        return new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());
    }

}
