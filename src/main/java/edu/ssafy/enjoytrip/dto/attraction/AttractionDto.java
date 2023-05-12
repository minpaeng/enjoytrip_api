package edu.ssafy.enjoytrip.dto.attraction;

import lombok.Data;

@Data
public class AttractionDto {
	
	int contentId;
	int contentTypeId;
	String title;
	String addr1;
	String addr2;
	String zipCode;
	String tel;
	String firstImage;
	String firstImage2;
	int readCount;
	int sidoCode;
	int gugunCode;
	String latitude;
	String longitude;
	String mlevel;
	
}
