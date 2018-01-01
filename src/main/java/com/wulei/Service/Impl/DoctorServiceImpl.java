package com.wulei.Service.Impl;

        import com.wulei.DAO.DateDao;
        import com.wulei.DAO.OrderDao;
        import com.wulei.Entity.Date;
        import com.wulei.Entity.Doctor;
        import com.wulei.Entity.Order;
        import com.wulei.Service.CustomerService;
        import com.wulei.Service.DoctorService;
        import com.wulei.Util.OrderDateUtil;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.List;
        import java.util.Map;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DateDao dateDao;
    @Autowired
    CustomerService customerService;
    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderDateUtil orderDateUtil;

    //private OrderDateUtil orderDateUtil = new OrderDateUtil();

    public List<Date> getDateList(int doctorid) {
        return customerService.getDateList(doctorid);
    }

    public List<Order> getOrderList(int dateid) {
        return orderDao.getOrderListByDateId(dateid);
    }

    public int newDate(int doctorid, int maxnumber, Map<String, String> timeMap) {
        Date date = new Date();
        date.setDoctorid(doctorid);
        date.setMaxnumber(maxnumber);
        date.setNumber(maxnumber);
        date.setName(orderDateUtil.formatTime(timeMap));

        List<Date> unexpiredDateList = customerService.getDateList(doctorid);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date start, end;
        try{
            start = df.parse(timeMap.get("startTime"));
            end = df.parse(timeMap.get("endTime"));
        }catch(ParseException ex){
            ex.printStackTrace();
            return 0;
        }
        for(Date d : unexpiredDateList) {
            Map<String, String> m = orderDateUtil.parseTime(d.getName());
            java.util.Date dstart, dend;
            try {
                dstart = df.parse(m.get("startTime"));
                dend = df.parse(m.get("endTime"));
            }catch (ParseException ex){
                ex.printStackTrace();
                return 0;
            }

            if((start.before(dend) && dstart.before(start)) || (end.before(dend) && dstart.before(end))
                    || (start.before(dstart) && dend.before(end)) || (start.before(dstart) && dstart.before(end))
                    || (start.before(dend) && dend.before(end)) || (start.equals(dstart) && end.equals(dend))){
                return -1;
            }
        }
        //System.out.println(date);
        return dateDao.insertDate(date);
    }
}

