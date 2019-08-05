package com.fangzhizun.cowxgzh.datasource;

import org.apache.ibatis.annotations.Param;

public interface MyDataSourceMapper {

	//取得公司名字 ，没错就是这简单的dao层！
	MyDataSource getDataSource(@Param("name") String name);
	MyDataSource getComparyId(@Param("id") Integer id);
}
