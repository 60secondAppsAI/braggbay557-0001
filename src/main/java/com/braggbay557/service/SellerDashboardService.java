package com.braggbay557.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay557.domain.SellerDashboard;
import com.braggbay557.dto.SellerDashboardDTO;
import com.braggbay557.dto.SellerDashboardSearchDTO;
import com.braggbay557.dto.SellerDashboardPageDTO;
import com.braggbay557.dto.SellerDashboardConvertCriteriaDTO;
import com.braggbay557.service.GenericService;
import com.braggbay557.dto.common.RequestDTO;
import com.braggbay557.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SellerDashboardService extends GenericService<SellerDashboard, Integer> {

	List<SellerDashboard> findAll();

	ResultDTO addSellerDashboard(SellerDashboardDTO sellerDashboardDTO, RequestDTO requestDTO);

	ResultDTO updateSellerDashboard(SellerDashboardDTO sellerDashboardDTO, RequestDTO requestDTO);

    Page<SellerDashboard> getAllSellerDashboards(Pageable pageable);

    Page<SellerDashboard> getAllSellerDashboards(Specification<SellerDashboard> spec, Pageable pageable);

	ResponseEntity<SellerDashboardPageDTO> getSellerDashboards(SellerDashboardSearchDTO sellerDashboardSearchDTO);
	
	List<SellerDashboardDTO> convertSellerDashboardsToSellerDashboardDTOs(List<SellerDashboard> sellerDashboards, SellerDashboardConvertCriteriaDTO convertCriteria);

	SellerDashboardDTO getSellerDashboardDTOById(Integer sellerDashboardId);







}





