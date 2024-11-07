package com.braggbay557.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay557.domain.BuyerDashboard;
import com.braggbay557.dto.BuyerDashboardDTO;
import com.braggbay557.dto.BuyerDashboardSearchDTO;
import com.braggbay557.dto.BuyerDashboardPageDTO;
import com.braggbay557.dto.BuyerDashboardConvertCriteriaDTO;
import com.braggbay557.service.GenericService;
import com.braggbay557.dto.common.RequestDTO;
import com.braggbay557.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface BuyerDashboardService extends GenericService<BuyerDashboard, Integer> {

	List<BuyerDashboard> findAll();

	ResultDTO addBuyerDashboard(BuyerDashboardDTO buyerDashboardDTO, RequestDTO requestDTO);

	ResultDTO updateBuyerDashboard(BuyerDashboardDTO buyerDashboardDTO, RequestDTO requestDTO);

    Page<BuyerDashboard> getAllBuyerDashboards(Pageable pageable);

    Page<BuyerDashboard> getAllBuyerDashboards(Specification<BuyerDashboard> spec, Pageable pageable);

	ResponseEntity<BuyerDashboardPageDTO> getBuyerDashboards(BuyerDashboardSearchDTO buyerDashboardSearchDTO);
	
	List<BuyerDashboardDTO> convertBuyerDashboardsToBuyerDashboardDTOs(List<BuyerDashboard> buyerDashboards, BuyerDashboardConvertCriteriaDTO convertCriteria);

	BuyerDashboardDTO getBuyerDashboardDTOById(Integer buyerDashboardId);







}





