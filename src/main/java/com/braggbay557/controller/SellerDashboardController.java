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

import com.braggbay557.domain.SellerDashboard;
import com.braggbay557.dto.SellerDashboardDTO;
import com.braggbay557.dto.SellerDashboardSearchDTO;
import com.braggbay557.dto.SellerDashboardPageDTO;
import com.braggbay557.service.SellerDashboardService;
import com.braggbay557.dto.common.RequestDTO;
import com.braggbay557.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/sellerDashboard")
@RestController
public class SellerDashboardController {

	private final static Logger logger = LoggerFactory.getLogger(SellerDashboardController.class);

	@Autowired
	SellerDashboardService sellerDashboardService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<SellerDashboard> getAll() {

		List<SellerDashboard> sellerDashboards = sellerDashboardService.findAll();
		
		return sellerDashboards;	
	}

	@GetMapping(value = "/{sellerDashboardId}")
	@ResponseBody
	public SellerDashboardDTO getSellerDashboard(@PathVariable Integer sellerDashboardId) {
		
		return (sellerDashboardService.getSellerDashboardDTOById(sellerDashboardId));
	}

 	@RequestMapping(value = "/addSellerDashboard", method = RequestMethod.POST)
	public ResponseEntity<?> addSellerDashboard(@RequestBody SellerDashboardDTO sellerDashboardDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = sellerDashboardService.addSellerDashboard(sellerDashboardDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/sellerDashboards")
	public ResponseEntity<SellerDashboardPageDTO> getSellerDashboards(SellerDashboardSearchDTO sellerDashboardSearchDTO) {
 
		return sellerDashboardService.getSellerDashboards(sellerDashboardSearchDTO);
	}	

	@RequestMapping(value = "/updateSellerDashboard", method = RequestMethod.POST)
	public ResponseEntity<?> updateSellerDashboard(@RequestBody SellerDashboardDTO sellerDashboardDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = sellerDashboardService.updateSellerDashboard(sellerDashboardDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
