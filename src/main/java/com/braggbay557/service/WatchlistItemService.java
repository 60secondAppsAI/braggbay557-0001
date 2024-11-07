package com.braggbay557.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay557.domain.WatchlistItem;
import com.braggbay557.dto.WatchlistItemDTO;
import com.braggbay557.dto.WatchlistItemSearchDTO;
import com.braggbay557.dto.WatchlistItemPageDTO;
import com.braggbay557.dto.WatchlistItemConvertCriteriaDTO;
import com.braggbay557.service.GenericService;
import com.braggbay557.dto.common.RequestDTO;
import com.braggbay557.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface WatchlistItemService extends GenericService<WatchlistItem, Integer> {

	List<WatchlistItem> findAll();

	ResultDTO addWatchlistItem(WatchlistItemDTO watchlistItemDTO, RequestDTO requestDTO);

	ResultDTO updateWatchlistItem(WatchlistItemDTO watchlistItemDTO, RequestDTO requestDTO);

    Page<WatchlistItem> getAllWatchlistItems(Pageable pageable);

    Page<WatchlistItem> getAllWatchlistItems(Specification<WatchlistItem> spec, Pageable pageable);

	ResponseEntity<WatchlistItemPageDTO> getWatchlistItems(WatchlistItemSearchDTO watchlistItemSearchDTO);
	
	List<WatchlistItemDTO> convertWatchlistItemsToWatchlistItemDTOs(List<WatchlistItem> watchlistItems, WatchlistItemConvertCriteriaDTO convertCriteria);

	WatchlistItemDTO getWatchlistItemDTOById(Integer watchlistItemId);







}





