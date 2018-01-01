package com.wulei.DAO;

import com.wulei.Entity.Date;

import java.util.List;

public interface DateDao {

    //新增时间段
    public int insertDate(com.wulei.Entity.Date date);

    //修改时间段
    public int updateDate(com.wulei.Entity.Date date);

    //新增预约，剩余可预约数-1
    public int makeNumberSubOne(int dateid);

    //删除预约,剩余可预约数+1
    public int makeNumberAddOne(int dateid);

    //根据doctorid查询某个医生的就诊时间表
    public List<Date> getDateListByDoctorId(int doctorid);

    //查询所有的Date（只返回dateid和name）
    public List<Date> getAllDate();
}
