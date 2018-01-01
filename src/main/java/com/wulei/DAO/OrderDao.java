package com.wulei.DAO;

import com.wulei.Entity.Order;

import java.util.List;

public interface OrderDao {

    //新增预约
    public int insertOrder(Order order);

    //修改预约
    public int updateOrder(Order order);

    //删除预约
    public int deleteOrder(int orderId);

    //通过orderid查询某一个order
    public Order getOrder(int orderId);

    //通过customerid和dateid查询预约,用Order类型封装
    public Order queryOrder(Order order);

    //查询某个医生的所有预约
    public List<Order> getOrderListByDoctorId(int doctorId);

    //查询某个用户的所有预约
    public List<Order> getOrderListByCustomerId(int customerId);

    //查询某个时间段的所有预约
    public List<Order> getOrderListByDateId(int dateId);
}
