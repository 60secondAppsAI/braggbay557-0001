package com.braggbay557.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.braggbay557.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.braggbay557.domain.Refund;
import com.braggbay557.dto.RefundDTO;
import com.braggbay557.dto.RefundSearchDTO;
import com.braggbay557.dto.RefundPageDTO;
import com.braggbay557.service.RefundService;
import com.braggbay557.dto.common.RequestDTO;
import com.braggbay557.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/refund")
@RestController
public class RefundController {

	private final static Logger logger = LoggerFactory.getLogger(RefundController.class);

	@Autowired
	RefundService refundService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Refund> getAll() {

		List<Refund> refunds = refundService.findAll();
		
		return refunds;	
	}

	@GetMapping(value = "/{refundId}")
	@ResponseBody
	public RefundDTO getRefund(@PathVariable Integer refundId) {
		
		return (refundService.getRefundDTOById(refundId));
	}

 	@RequestMapping(value = "/addRefund", method = RequestMethod.POST)
	public ResponseEntity<?> addRefund(@RequestBody RefundDTO refundDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = refundService.addRefund(refundDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/refunds")
	public ResponseEntity<RefundPageDTO> getRefunds(RefundSearchDTO refundSearchDTO) {
 
		return refundService.getRefunds(refundSearchDTO);
	}	

	@RequestMapping(value = "/updateRefund", method = RequestMethod.POST)
	public ResponseEntity<?> updateRefund(@RequestBody RefundDTO refundDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = refundService.updateRefund(refundDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
