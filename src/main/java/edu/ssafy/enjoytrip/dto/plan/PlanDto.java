package edu.ssafy.enjoytrip.dto.plan;

import lombok.Data;

@Data
public class PlanDto {
    int id;
    String userId;
    String startDate;
    String endDate;
    String memo;
    String title;
    String registerDate;
    String share;
}
