package com.wulei.DAO;

import com.wulei.Entity.Doctor;

import java.util.List;

public interface DoctorDao {

    //新增医生
    public int insertDoctor(Doctor doctor);

    //修改医生信息
    public int updateDoctor(Doctor dortor);

    //根据doctorid查询医生
    public Doctor getDoctorByDoctorId(int doctorid);

    //查询某个科室所有的医生
    public List<Doctor> getDoctorList(int categoryid);

    //根据医生姓名模糊搜索医生
    public Doctor searchDoctor(String name);
}
