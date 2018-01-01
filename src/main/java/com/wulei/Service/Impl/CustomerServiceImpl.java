package com.wulei.Service.Impl;

import com.wulei.DAO.*;
import com.wulei.Entity.*;
import com.wulei.Service.CustomerService;
import com.wulei.Util.OrderDateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CategoryDao categoryDao;
    @Autowired
    DoctorDao doctorDao;
    @Autowired
    DateDao dateDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    CustomerDao customerDao;
    @Autowired
    ExpiredDateDao expiredDateDao;
    @Autowired
    CustomerService customerService;
    @Autowired
    OrderDateUtil orderDateUtil;

    //private OrderDateUtil orderDateUtil = new OrderDateUtil();

    public List<Category> getCategoryList() {
        List<Category> categoryList = categoryDao.getCategoryList();
        return categoryList;
    }

    public List<Doctor> getDoctorList(int categoryId) {
        List<Doctor> doctorList = doctorDao.getDoctorList(categoryId);
        Logger logger = Logger.getLogger(CustomerServiceImpl.class);
        for(Doctor doctor : doctorList)
            logger.debug(doctor);
        return doctorList;
    }

    /**
     * 查询某个医生未过期的就诊时间段
     * @param doctorid
     * @return
     */
    public List<Date> getDateList(int doctorid) {
        List<Date> dateList = dateDao.getDateListByDoctorId(doctorid);
        List<Date> dl = new ArrayList<Date>();
        for(Date date : dateList){
            if(expiredDateDao.isExpired(date.getDateid()) != null)
                continue;
            dl.add(date);
        }
        return dl;
    }

    public int newOrder(Order order){
        if(orderDao.queryOrder(order) != null)
            return -1;
        order.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
        order.setStatus("Available");
        dateDao.makeNumberSubOne(order.getDateid());
        return orderDao.insertOrder(order);
    }

    public int deleteOrder(int orderId) {
        Order order = orderDao.getOrder(orderId);
        if(order != null){
            int flag = orderDao.deleteOrder(orderId);
            if(flag == 1){
                dateDao.makeNumberAddOne(order.getDateid());
                return flag;
            }
        }
        return 0;
    }

    public List<Order> getOrderList(int customerid) {
        List<Order> orderList = orderDao.getOrderListByCustomerId(customerid);
        return orderList;
    }

    /**
     * 添加就诊人
     * @param customer
     * @return 添加成功，返回1，否则返回0
     */
    public int newCustomer(Customer customer) {
        if(customer.getName() != null && customer.getMobile() != null){
            return customerDao.insertCustomer(customer);
        }
        return 0;
    }

    /**
     * 根据参数customer中的name和mobile匹配数据库中是否存在该就诊人
     * @param customer
     * @return 如果存在就诊人，返回customerid,不存在返回0
     */
    public int queryCustomer(Customer customer) {
        if(customer.getName() != null && customer.getMobile() != null){
            Customer c = customerDao.getCustomerByNameAndMobile(customer);
            if(c != null)
                return c.getCustomerid();
        }
        return 0;
    }

    public Object searchCategoryOrDoctor(String name) {
        Category category = categoryDao.getCategoryByName(name);
        Doctor doctor = doctorDao.searchDoctor(name);
        if(category != null && doctor == null){
            return category;
        }
        else if(category == null && doctor != null){
            return doctor;
        }
        else{
            return null;
        }
    }

    public Date searchDate(int catid, String time) {
        List<Doctor> doctorList = doctorDao.getDoctorList(catid);
        List<Date> dateList = new ArrayList<Date>();
        for(Doctor doctor : doctorList){
            List<Date> dl = customerService.getDateList(doctor.getDoctorid());
            dateList.addAll(dl);
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date userDate;
        try{
            userDate = df.parse(time);
        }catch(ParseException ex){
            ex.printStackTrace();
            return null;
        }
        for(Date date : dateList){
            Map<String, String> timeMap = orderDateUtil.parseTime(date.getName());
            try{
                java.util.Date startTime = df.parse(timeMap.get("startTime"));
                java.util.Date endTime = df.parse(timeMap.get("endTime"));
                if(startTime.before(userDate) && userDate.before(endTime)){
                    return date;
                }

            }catch(ParseException ex){
                ex.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
