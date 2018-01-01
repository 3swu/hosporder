package com.wulei.DAO;

import com.wulei.Entity.ExpiredDate;

import java.util.List;

public interface ExpiredDateDao {
    //新增过期Date项
    public int insertExpiredDate(int dateid);

    //查询某个dateid是否存在于表中
    public ExpiredDate isExpired(int dateid);

    //得到所有过期dateid
    public List<ExpiredDate> getAllExpiredDate();
}
