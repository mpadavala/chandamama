package com.sample.store.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.store.pojo.SamplePojo;

@RestController
@RequestMapping("/hi")
public class SampleController {
	
	@RequestMapping(value="/hello1")
	public String returnString(){
		return "Hello";
	}
	
	@RequestMapping(value="/hello2")
	public SamplePojo returnPojo(){
		SamplePojo pojo = new SamplePojo();
		pojo.setId(1);
		pojo.setStr("Hello in Pojo");
		return pojo;
	}
	
	@RequestMapping(value="/hello3")
	public List<SamplePojo> returnPojoList(){
		
		List<SamplePojo> list = new ArrayList<>();
		
		SamplePojo pojo1 = new SamplePojo();
		pojo1.setId(1);
		pojo1.setStr("Hello in Pojo - 1");
		list.add(pojo1);
		
		SamplePojo pojo2 = new SamplePojo();
		pojo2.setId(2);
		pojo2.setStr("Hello in Pojo - 2");
		list.add(pojo2);
		
		return list;
	}
}
