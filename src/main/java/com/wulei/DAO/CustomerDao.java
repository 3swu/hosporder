package com.wulei.DAO;


import com.wulei.Entity.Customer;

public interface CustomerDao {

    //新增用户信息,Customer对象中需包含name和mobile字段
    public int insertCustomer(Customer customer);

    //通过customerid得到对应Customer对象
    public Customer getCustomerById(int customerid);

    //通过name和mobile得到完整Customer对象
    public Customer getCustomerByNameAndMobile(Customer customer);
}
