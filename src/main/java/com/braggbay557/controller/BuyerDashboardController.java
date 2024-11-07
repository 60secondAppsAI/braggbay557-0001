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

import com.braggbay557.domain.BuyerDashboard;
import com.braggbay557.dto.BuyerDashboardDTO;
import com.braggbay557.dto.BuyerDashboardSearchDTO;
import com.braggbay557.dto.BuyerDashboardPageDTO;
import com.braggbay557.service.BuyerDashboardService;
import com.braggbay557.dto.common.RequestDTO;
import com.braggbay557.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/buyerDashboard")
@RestController
public class BuyerDashboardController {

	private final static Logger logger = LoggerFactory.getLogger(BuyerDashboardController.class);

	@Autowired
	BuyerDashboardService buyerDashboardService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<BuyerDashboard> getAll() {

		List<BuyerDashboard> buyerDashboards = buyerDashboardService.findAll();
		
		return buyerDashboards;	
	}

	@GetMapping(value = "/{buyerDashboardId}")
	@ResponseBody
	public BuyerDashboardDTO getBuyerDashboard(@PathVariable Integer buyerDashboardId) {
		
		return (buyerDashboardService.getBuyerDashboardDTOById(buyerDashboardId));
	}

 	@RequestMapping(value = "/addBuyerDashboard", method = RequestMethod.POST)
	public ResponseEntity<?> addBuyerDashboard(@RequestBody BuyerDashboardDTO buyerDashboardDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = buyerDashboardService.addBuyerDashboard(buyerDashboardDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/buyerDashboards")
	public ResponseEntity<BuyerDashboardPageDTO> getBuyerDashboards(BuyerDashboardSearchDTO buyerDashboardSearchDTO) {
 
		return buyerDashboardService.getBuyerDashboards(buyerDashboardSearchDTO);
	}	

	@RequestMapping(value = "/updateBuyerDashboard", method = RequestMethod.POST)
	public ResponseEntity<?> updateBuyerDashboard(@RequestBody BuyerDashboardDTO buyerDashboardDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = buyerDashboardService.updateBuyerDashboard(buyerDashboardDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
