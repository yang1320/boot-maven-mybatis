package com.slcf.inter.impl;

import java.util.List;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slcf.bean.Dept;
import com.slcf.inter.DeptInter;
import com.slcf.mapper.DeptMapper;
import com.slcf.mapper.EmpMapper;


@Transactional(rollbackFor=Exception.class)
@Service			
public class DeptInterImpl implements DeptInter {
	@Autowired
	private DeptMapper dm;
	@Autowired
	private EmpMapper em;

	public List<Dept> selectAll() {
		// TODO Auto-generated method stub
		return dm.selectAll();
	}
	
	public int  deleteOne(Integer id) throws Exception{
		System.out.println("dddddd");
		Integer count1=0,count=0;
		
		try {
			count1=em.deleteByDeptNo(id);
			//int a =2/0;
			count=dm.deleteOne(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("数据库异常！"+e);
			//throw new RuntimeException(e);
		}

		
		
		return count;
		
	}
	


}
