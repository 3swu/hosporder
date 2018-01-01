package com.wulei.Util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class OrderDateUtil {

    //判断就诊时间是否过期
    public boolean isDateExpired(String dateStr){
        Date nowDate = new Date();
        Map<String, String> dateMap = new OrderDateUtil().parseTime(dateStr);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endDate;
        try{
            endDate = df.parse(dateMap.get("endTime"));
        }catch(ParseException ex){
            ex.printStackTrace();
            return true;
        }
        if(endDate.before(nowDate))
            return true;
        return false;
    }

    /**
     * 将表示时间段的字符串解析为开始时间和结束时间
     * @param dateStr 格式：“星期几@日期@开始时刻@结束时刻”
     * @return
     */
    public Map<String, String> parseTime(String dateStr){
        String[] strings = dateStr.split("@");
        String startTime = strings[1] + " " + strings[2];
        String endTime = strings[1] + " " + strings[3];
        Map<String, String> timeMap = new HashMap<String, String>();
        timeMap.put("startTime", startTime);
        timeMap.put("endTime", endTime);
        return timeMap;
    }

    public String formatTime(Map<String, String> dateMap){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String weekDays[] = {"星期日", "星期一", "星期二", "星期三" ,"星期四" ,"星期五" ,"星期六"};
        Calendar calendar = Calendar.getInstance();
        Date startDate;
        Date endDate;
        try{
            startDate = simpleDateFormat.parse(dateMap.get("startTime"));
            endDate = simpleDateFormat.parse(dateMap.get("endTime"));
        }catch(ParseException ex){
            ex.printStackTrace();
            return null;
        }
        StringBuffer sb = new StringBuffer();
        calendar.setTime(startDate);
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if(w < 0)
            w = 0;
        sb.append(weekDays[w]);
        sb.append("@");
        sb.append(dateFormat.format(startDate));
        sb.append("@");
        sb.append(timeFormat.format(startDate));
        sb.append("@");
        sb.append(timeFormat.format(endDate));
        return sb.toString();
    }

//    public static void main(String[] args){
//        OrderDateUtil orderDateUtil = new OrderDateUtil();
//        String dateStr = "星期一@2017-12-14@19:22:00@19:22:00";
//        System.out.println(orderDateUtil.isDateExpired(dateStr));
//
//        Map<String, String> m = new HashMap<String, String>();
//        m.put("startTime", "2017-12-19 18:00:00");
//        m.put("endTime", "2017-12-19 18:30:00");
//        System.out.println(new OrderDateUtil().formatTime(m));
//
//    }
}
