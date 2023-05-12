package edu.ssafy.enjoytrip.dto.review;

import lombok.Data;

@Data
public class ReviewDto {

    int reviewId;
    String userId;
    int planId;
    String title;
    String content;
    String registerTime;
    int hit;
    int like;

}
