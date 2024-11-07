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

import com.braggbay557.domain.WatchlistItem;
import com.braggbay557.dto.WatchlistItemDTO;
import com.braggbay557.dto.WatchlistItemSearchDTO;
import com.braggbay557.dto.WatchlistItemPageDTO;
import com.braggbay557.service.WatchlistItemService;
import com.braggbay557.dto.common.RequestDTO;
import com.braggbay557.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/watchlistItem")
@RestController
public class WatchlistItemController {

	private final static Logger logger = LoggerFactory.getLogger(WatchlistItemController.class);

	@Autowired
	WatchlistItemService watchlistItemService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<WatchlistItem> getAll() {

		List<WatchlistItem> watchlistItems = watchlistItemService.findAll();
		
		return watchlistItems;	
	}

	@GetMapping(value = "/{watchlistItemId}")
	@ResponseBody
	public WatchlistItemDTO getWatchlistItem(@PathVariable Integer watchlistItemId) {
		
		return (watchlistItemService.getWatchlistItemDTOById(watchlistItemId));
	}

 	@RequestMapping(value = "/addWatchlistItem", method = RequestMethod.POST)
	public ResponseEntity<?> addWatchlistItem(@RequestBody WatchlistItemDTO watchlistItemDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = watchlistItemService.addWatchlistItem(watchlistItemDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/watchlistItems")
	public ResponseEntity<WatchlistItemPageDTO> getWatchlistItems(WatchlistItemSearchDTO watchlistItemSearchDTO) {
 
		return watchlistItemService.getWatchlistItems(watchlistItemSearchDTO);
	}	

	@RequestMapping(value = "/updateWatchlistItem", method = RequestMethod.POST)
	public ResponseEntity<?> updateWatchlistItem(@RequestBody WatchlistItemDTO watchlistItemDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = watchlistItemService.updateWatchlistItem(watchlistItemDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
