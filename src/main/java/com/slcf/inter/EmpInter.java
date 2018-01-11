package com.slcf.inter;


import java.util.List;






import com.slcf.bean.Emp;



public interface EmpInter {
	
	public List<Emp> selectAll();
	public int deleteOne(Integer id);
	public int deleteByDeptNo(Integer deptNo);
	
}
