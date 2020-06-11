package com.my.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.my.DTO.MemberDTO;

public interface MapperDAO {
	public ArrayList<MemberDTO> selectAll();
	public void deleteAll();
	
	// 인터페이스에서 mybatis에 접근할 때, 매개변수에 String을 두 개 이상을 사용할 수 없다.
	//둘 이상의 변수를 전달하고자 할 때, @Param 어노테이션을 이용하는 방법, 객체를 이용하는 방법, 해쉬맵을 이용하는 방법이 있다.
	public void insert(@Param("id")String id, @Param("name")String name);
	public void del(String id);
	public MemberDTO selectOne(String id);
	public void modify(MemberDTO memberDTO);
	public void modifyhash(HashMap<String,String> map);
}
