package com.slcf.inter.impl;

import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slcf.bean.Emp;
import com.slcf.inter.EmpInter;



public class EmpInterImpl implements EmpInter{
	@Autowired
	private EmpInter et;

	public List<Emp> selectAll() {
		// TODO Auto-generated method stub
		return et.selectAll();
	}


	public int deleteOne(Integer id) {
		// TODO Auto-generated method stub
		return et.deleteOne(id);
	}

	public int deleteByDeptNo(Integer deptNo) {
		// TODO Auto-generated method stub
		return et.deleteByDeptNo(deptNo);
	}

}
