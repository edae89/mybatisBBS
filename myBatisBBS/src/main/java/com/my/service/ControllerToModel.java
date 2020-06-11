package com.my.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.my.DTO.MemberDTO;

public interface ControllerToModel {
	public ArrayList<MemberDTO> selectAll();
	public void insert(String id, String name);
	public void del(String id);
	public MemberDTO selectOne(String id);
	public void modify(MemberDTO user);
	public void modifyhash(HashMap<String, String> map);
	
}
