package com.wulei.Service;

import com.wulei.Entity.Date;
import com.wulei.Entity.Order;

import java.util.List;
import java.util.Map;

public interface DoctorService {

    //通过doctorid查询时间表列表
    public List<Date> getDateList(int doctorid);

    //通过dateid查询某个时间端的所有预约
    public List<Order> getOrderList(int dateid);

    //新增就诊时间段
    public int newDate(int doctorid, int maxnumber, Map<String, String> timeMap);
}
