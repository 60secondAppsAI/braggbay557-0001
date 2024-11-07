package com.braggbay557.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SellerDashboardPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<SellerDashboardDTO> sellerDashboards;
}





