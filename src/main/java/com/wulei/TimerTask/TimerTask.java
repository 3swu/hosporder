package com.wulei.TimerTask;

import com.wulei.DAO.DateDao;
import com.wulei.DAO.ExpiredDateDao;
import com.wulei.DAO.OrderDao;
import com.wulei.Entity.Date;
import com.wulei.Entity.ExpiredDate;
import com.wulei.Entity.Order;
import com.wulei.Util.OrderDateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TimerTask {
    @Autowired
    DateDao dateDao;
    @Autowired
    ExpiredDateDao expiredDateDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderDateUtil orderDateUtil;

    //private OrderDateUtil orderDateUtil = new OrderDateUtil();

    public void dateExpiredTask(){
        List<Date> allDate = dateDao.getAllDate();
        List<ExpiredDate> expiredDates = expiredDateDao.getAllExpiredDate();
        List<Integer> expiredDateids = new ArrayList<Integer>();
        List<Date> goingToCheckDates = new ArrayList<Date>();
        for(ExpiredDate ed : expiredDates){
            expiredDateids.add(ed.getDateid());
        }

        for(Date date : allDate){
            if(expiredDateids.contains(date.getDateid())){
                continue;
            }
            goingToCheckDates.add(date);
        }

        for(Date d : goingToCheckDates){
            if(orderDateUtil.isDateExpired(d.getName())){
                expiredDateDao.insertExpiredDate(d.getDateid());
                List<Order> orderList = orderDao.getOrderListByDateId(d.getDateid());
                for(Order o : orderList){
                    o.setStatus("Expired");
                    orderDao.updateOrder(o);
                }
            }
        }

    }
}
