package edu.ssafy.enjoytrip.dto.review;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ReviewDto {

    int reviewId;
    String userId;
    int planId;
    String title;
    String content;
    String visitDate;
    String registerTime;
    int hit;
}
