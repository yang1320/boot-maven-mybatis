package com.slcf.inter;


import java.util.List;





import com.slcf.bean.Dept;



public interface DeptInter {
	
	public List<Dept> selectAll();
	public int deleteOne(Integer id) throws Exception;
	
}
