package edu.ssafy.enjoytrip.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SizeConstant {

	public static int LIST_SIZE;
	public static int NAVIGATION_SIZE;
	
	public SizeConstant(@Value("${size.list}") int listSize, 
						@Value("${size.nav}") int navSize) {
		LIST_SIZE = listSize;
		NAVIGATION_SIZE = navSize;
	}

}