package com.braggbay557.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.braggbay557.dao.GenericDAO;
import com.braggbay557.service.GenericService;
import com.braggbay557.service.impl.GenericServiceImpl;
import com.braggbay557.dao.WatchlistItemDAO;
import com.braggbay557.domain.WatchlistItem;
import com.braggbay557.dto.WatchlistItemDTO;
import com.braggbay557.dto.WatchlistItemSearchDTO;
import com.braggbay557.dto.WatchlistItemPageDTO;
import com.braggbay557.dto.WatchlistItemConvertCriteriaDTO;
import com.braggbay557.dto.common.RequestDTO;
import com.braggbay557.dto.common.ResultDTO;
import com.braggbay557.service.WatchlistItemService;
import com.braggbay557.util.ControllerUtils;





@Service
public class WatchlistItemServiceImpl extends GenericServiceImpl<WatchlistItem, Integer> implements WatchlistItemService {

    private final static Logger logger = LoggerFactory.getLogger(WatchlistItemServiceImpl.class);

	@Autowired
	WatchlistItemDAO watchlistItemDao;

	


	@Override
	public GenericDAO<WatchlistItem, Integer> getDAO() {
		return (GenericDAO<WatchlistItem, Integer>) watchlistItemDao;
	}
	
	public List<WatchlistItem> findAll () {
		List<WatchlistItem> watchlistItems = watchlistItemDao.findAll();
		
		return watchlistItems;	
		
	}

	public ResultDTO addWatchlistItem(WatchlistItemDTO watchlistItemDTO, RequestDTO requestDTO) {

		WatchlistItem watchlistItem = new WatchlistItem();

		watchlistItem.setWatchlistItemId(watchlistItemDTO.getWatchlistItemId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		watchlistItem = watchlistItemDao.save(watchlistItem);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<WatchlistItem> getAllWatchlistItems(Pageable pageable) {
		return watchlistItemDao.findAll(pageable);
	}

	public Page<WatchlistItem> getAllWatchlistItems(Specification<WatchlistItem> spec, Pageable pageable) {
		return watchlistItemDao.findAll(spec, pageable);
	}

	public ResponseEntity<WatchlistItemPageDTO> getWatchlistItems(WatchlistItemSearchDTO watchlistItemSearchDTO) {
	
			Integer watchlistItemId = watchlistItemSearchDTO.getWatchlistItemId(); 
 			String sortBy = watchlistItemSearchDTO.getSortBy();
			String sortOrder = watchlistItemSearchDTO.getSortOrder();
			String searchQuery = watchlistItemSearchDTO.getSearchQuery();
			Integer page = watchlistItemSearchDTO.getPage();
			Integer size = watchlistItemSearchDTO.getSize();

	        Specification<WatchlistItem> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, watchlistItemId, "watchlistItemId"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<WatchlistItem> watchlistItems = this.getAllWatchlistItems(spec, pageable);
		
		//System.out.println(String.valueOf(watchlistItems.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(watchlistItems.getTotalPages()));
		
		List<WatchlistItem> watchlistItemsList = watchlistItems.getContent();
		
		WatchlistItemConvertCriteriaDTO convertCriteria = new WatchlistItemConvertCriteriaDTO();
		List<WatchlistItemDTO> watchlistItemDTOs = this.convertWatchlistItemsToWatchlistItemDTOs(watchlistItemsList,convertCriteria);
		
		WatchlistItemPageDTO watchlistItemPageDTO = new WatchlistItemPageDTO();
		watchlistItemPageDTO.setWatchlistItems(watchlistItemDTOs);
		watchlistItemPageDTO.setTotalElements(watchlistItems.getTotalElements());
		return ResponseEntity.ok(watchlistItemPageDTO);
	}

	public List<WatchlistItemDTO> convertWatchlistItemsToWatchlistItemDTOs(List<WatchlistItem> watchlistItems, WatchlistItemConvertCriteriaDTO convertCriteria) {
		
		List<WatchlistItemDTO> watchlistItemDTOs = new ArrayList<WatchlistItemDTO>();
		
		for (WatchlistItem watchlistItem : watchlistItems) {
			watchlistItemDTOs.add(convertWatchlistItemToWatchlistItemDTO(watchlistItem,convertCriteria));
		}
		
		return watchlistItemDTOs;

	}
	
	public WatchlistItemDTO convertWatchlistItemToWatchlistItemDTO(WatchlistItem watchlistItem, WatchlistItemConvertCriteriaDTO convertCriteria) {
		
		WatchlistItemDTO watchlistItemDTO = new WatchlistItemDTO();
		
		watchlistItemDTO.setWatchlistItemId(watchlistItem.getWatchlistItemId());

	

		
		return watchlistItemDTO;
	}

	public ResultDTO updateWatchlistItem(WatchlistItemDTO watchlistItemDTO, RequestDTO requestDTO) {
		
		WatchlistItem watchlistItem = watchlistItemDao.getById(watchlistItemDTO.getWatchlistItemId());

		watchlistItem.setWatchlistItemId(ControllerUtils.setValue(watchlistItem.getWatchlistItemId(), watchlistItemDTO.getWatchlistItemId()));



        watchlistItem = watchlistItemDao.save(watchlistItem);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public WatchlistItemDTO getWatchlistItemDTOById(Integer watchlistItemId) {
	
		WatchlistItem watchlistItem = watchlistItemDao.getById(watchlistItemId);
			
		
		WatchlistItemConvertCriteriaDTO convertCriteria = new WatchlistItemConvertCriteriaDTO();
		return(this.convertWatchlistItemToWatchlistItemDTO(watchlistItem,convertCriteria));
	}







}
