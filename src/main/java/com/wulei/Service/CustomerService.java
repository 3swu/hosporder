package com.wulei.Service;

import com.wulei.Entity.*;

import java.util.List;

public interface CustomerService {

    //获取所有科室的列表
    public List<Category> getCategoryList();

    //获取某个科室的所有医生列表
    public List<Doctor> getDoctorList(int categoryId);

    //获取某个医生的就诊时间段列表
    public List<Date> getDateList(int doctorid);

    //生成预约
    public int newOrder(Order order);

    //取消预约
    public int deleteOrder(int orderId);

    //查询用户所有的预约
    public List<Order> getOrderList(int customerid);

    //添加就诊人
    public int newCustomer(Customer customer);

    //根据姓名和电话查询数据库中是否存在该就诊人
    public int queryCustomer(Customer customer);

    //搜索科室或者医生
    public Object searchCategoryOrDoctor(String name);

    //通过catid和time来匹配某一科室中匹配的就诊时间段
    public Date searchDate(int catid, String time);
}
