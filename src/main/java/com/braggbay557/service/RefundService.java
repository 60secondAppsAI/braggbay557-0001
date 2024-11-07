package com.braggbay557.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay557.domain.Refund;
import com.braggbay557.dto.RefundDTO;
import com.braggbay557.dto.RefundSearchDTO;
import com.braggbay557.dto.RefundPageDTO;
import com.braggbay557.dto.RefundConvertCriteriaDTO;
import com.braggbay557.service.GenericService;
import com.braggbay557.dto.common.RequestDTO;
import com.braggbay557.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RefundService extends GenericService<Refund, Integer> {

	List<Refund> findAll();

	ResultDTO addRefund(RefundDTO refundDTO, RequestDTO requestDTO);

	ResultDTO updateRefund(RefundDTO refundDTO, RequestDTO requestDTO);

    Page<Refund> getAllRefunds(Pageable pageable);

    Page<Refund> getAllRefunds(Specification<Refund> spec, Pageable pageable);

	ResponseEntity<RefundPageDTO> getRefunds(RefundSearchDTO refundSearchDTO);
	
	List<RefundDTO> convertRefundsToRefundDTOs(List<Refund> refunds, RefundConvertCriteriaDTO convertCriteria);

	RefundDTO getRefundDTOById(Integer refundId);







}





