package com.wulei.Controller;


import com.wulei.Entity.*;
import com.wulei.Service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public @ResponseBody List<Category> getCategoryList(){
        List<Category> categoryList = customerService.getCategoryList();
        if(categoryList.size() > 0)
            return categoryList;
        return null;
    }

    @RequestMapping(value = "/doctors", method = RequestMethod.GET)
    public @ResponseBody List<Doctor> getDoctorList(@RequestParam int categoryid){
        List<Doctor> doctorList = customerService.getDoctorList(categoryid);
        if(doctorList.size() > 0)
            return doctorList;
        return null;
    }

    @RequestMapping(value = "/dates", method = RequestMethod.GET)
    public @ResponseBody List<Date> getDateList(@RequestParam int doctorid){
        List<Date> dateList = customerService.getDateList(doctorid);
        if(dateList.size() > 0)
            return dateList;
        return null;
    }

    @RequestMapping(value = "/date", method = RequestMethod.GET)
    public @ResponseBody Date searchDate(@RequestParam String time, @RequestParam int catid){
        return customerService.searchDate(catid, time);
    }

    @RequestMapping(value = "/order", method = RequestMethod.PUT)
    public @ResponseBody
    Response newOrder(@RequestBody Order order) {
        Response response = new Response();
        if(order.getCustomerid() > 0 && order.getDateid()> 0){
//            Order order = new Order();
//            order.setCustomerid(customerid.intValue());
//            order.setDateid(dateid.intValue());
            int flag = customerService.newOrder(order);
            if(flag == -1){
                response.setCode(0);
                response.setMsg("在该时段已有预约");
                return response;
            }
            response.setCode(flag);
            response.setMsg("预约成功");
            Logger logger = Logger.getLogger(CustomerController.class);
            logger.debug(order.toString());
            return response;
        }
        response.setCode(0);
        response.setMsg("预约失败");
        return response;
    }

    @RequestMapping(value = "/order", method = RequestMethod.DELETE)
    public @ResponseBody
    Response deleteOrder(@RequestBody Map<String,Integer> map){
        int orderid = map.get("orderid");
        if(customerService.deleteOrder(orderid) == 1)
            return new Response(1,"取消成功");
        return new Response(0,"取消失败");
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public @ResponseBody List<Order> getOrderList(@RequestParam int customerid){
        List<Order> orderList = customerService.getOrderList(customerid);
        if(orderList.size() != 0)
            return orderList;
        return null;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public @ResponseBody Response newCustomer(@RequestBody Customer customer){

        Logger logger = Logger.getLogger(CustomerController.class);
        logger.debug(customer.toString());

        Response response = new Response();
        int queryResult = customerService.queryCustomer(customer);
        if(queryResult != 0){
            response.setCode(queryResult);
            response.setMsg("就诊人已存在");
            return response;
        }
        else{
            int insertResult = customerService.newCustomer(customer);
            if(insertResult != 0){
                response.setCode(customerService.queryCustomer(customer));
                response.setMsg("添加就诊人信息成功");
                return response;
            }
            else{
                response.setCode(insertResult);
                response.setMsg("添加就诊人信息失败");
                return response;
            }
        }
    }

    @RequestMapping(value = "/category-doctor", method = RequestMethod.GET)
    public @ResponseBody Object searchCategoryOrDoctor(@RequestParam String name){
        return customerService.searchCategoryOrDoctor(name);
    }
}
