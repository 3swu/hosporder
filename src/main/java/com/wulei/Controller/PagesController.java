package com.wulei.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/pages")
public class PagesController {

    @RequestMapping(value = "/customer/categorylist", method = RequestMethod.GET)
    public String categoryList(){
        return "categorylist";
    }

    @RequestMapping(value = "/customer/doctor", method = RequestMethod.GET)
    public String doctorlist(){
        return "doctorlist";
    }

    @RequestMapping(value = "/customer/customer", method = RequestMethod.GET)
    public String custoemr(){
        return "customer";
    }
}
