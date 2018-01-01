package com.wulei.Controller;

import com.wulei.Entity.Date;
import com.wulei.Entity.Order;
import com.wulei.Entity.Response;
import com.wulei.Service.DoctorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @RequestMapping(value = "/date", method = RequestMethod.GET)
    public @ResponseBody
    List<Date> getDateList(@RequestParam int doctorid){
        return doctorService.getDateList(doctorid);
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public @ResponseBody List<Order> getOrderList(@RequestParam int dateid){
        return doctorService.getOrderList(dateid);
    }

    @RequestMapping(value = "/date", method = RequestMethod.POST)
    public @ResponseBody
    Response newDate(@RequestBody Map<String, String> paramMap){
        Logger logger = Logger.getLogger(DoctorController.class);
        logger.debug(paramMap.toString());
        Map<String, String> timeMap = new HashMap<String, String>();
        timeMap.put("startTime", paramMap.get("startTime"));
        timeMap.put("endTime", paramMap.get("endTime"));
        int resultCode = doctorService.newDate(Integer.parseInt(paramMap.get("doctorid")), Integer.parseInt(paramMap.get("maxnumber")), timeMap);
        if(resultCode == 0){
            Response response = new Response(resultCode, "添加就诊时间失败");
            return response;
        }
        else if(resultCode == -1){
            Response response = new Response(0, "与已有就诊时间段冲突");
            return response;
        }
        Response response = new Response(resultCode, "添加就诊时间成功");
        return response;
    }
}
