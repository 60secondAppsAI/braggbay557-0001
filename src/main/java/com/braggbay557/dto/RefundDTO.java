package com.braggbay557.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class RefundDTO {

	private Integer refundId;

	private Date refundDate;

	private Double refundAmount;

	private String reason;






}
