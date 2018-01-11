package com.slcf.mapper;


import java.util.List;






import com.slcf.bean.Emp;



public interface EmpMapper {
	
	public List<Emp> selectAll();
	public int deleteOne(Integer id);
	public int deleteByDeptNo(Integer deptNo);
	
}
