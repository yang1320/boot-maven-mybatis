package com.slcf.mapper;


import java.util.List;




import com.slcf.bean.Dept;



public interface DeptMapper {
	
	public List<Dept> selectAll();
	public int deleteOne(Integer id);
	
}
