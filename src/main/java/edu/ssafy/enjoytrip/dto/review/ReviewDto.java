package edu.ssafy.enjoytrip.dto.review;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Builder
public class ReviewDto {

    int reviewId;
    String userId;
    String planId;
    String title;
    String content;
    String visitDate;
    String registerTime;
    int hit;
    String spotName;
    String spotAddress;
    String x;
    String y;
}
