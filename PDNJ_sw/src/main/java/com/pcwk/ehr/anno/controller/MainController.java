package com.pcwk.ehr.anno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


	// 화면 호출
	@RequestMapping(value = "/main/Main.do")
	public String annoView() {
		System.out.println("==========");
		System.out.println("=annoView=");
		System.out.println("==========");

		return "main/Main";
	}
	

	@RequestMapping(value = "/map/map.do")
	public String mapView() {
		System.out.println("==========");
		System.out.println("=annoView=");
		System.out.println("==========");

		return "map/map";
	}
	
}