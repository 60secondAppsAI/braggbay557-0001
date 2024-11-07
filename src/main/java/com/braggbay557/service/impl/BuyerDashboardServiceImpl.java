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
import com.braggbay557.dao.BuyerDashboardDAO;
import com.braggbay557.domain.BuyerDashboard;
import com.braggbay557.dto.BuyerDashboardDTO;
import com.braggbay557.dto.BuyerDashboardSearchDTO;
import com.braggbay557.dto.BuyerDashboardPageDTO;
import com.braggbay557.dto.BuyerDashboardConvertCriteriaDTO;
import com.braggbay557.dto.common.RequestDTO;
import com.braggbay557.dto.common.ResultDTO;
import com.braggbay557.service.BuyerDashboardService;
import com.braggbay557.util.ControllerUtils;





@Service
public class BuyerDashboardServiceImpl extends GenericServiceImpl<BuyerDashboard, Integer> implements BuyerDashboardService {

    private final static Logger logger = LoggerFactory.getLogger(BuyerDashboardServiceImpl.class);

	@Autowired
	BuyerDashboardDAO buyerDashboardDao;

	


	@Override
	public GenericDAO<BuyerDashboard, Integer> getDAO() {
		return (GenericDAO<BuyerDashboard, Integer>) buyerDashboardDao;
	}
	
	public List<BuyerDashboard> findAll () {
		List<BuyerDashboard> buyerDashboards = buyerDashboardDao.findAll();
		
		return buyerDashboards;	
		
	}

	public ResultDTO addBuyerDashboard(BuyerDashboardDTO buyerDashboardDTO, RequestDTO requestDTO) {

		BuyerDashboard buyerDashboard = new BuyerDashboard();

		buyerDashboard.setBuyerDashboardId(buyerDashboardDTO.getBuyerDashboardId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		buyerDashboard = buyerDashboardDao.save(buyerDashboard);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<BuyerDashboard> getAllBuyerDashboards(Pageable pageable) {
		return buyerDashboardDao.findAll(pageable);
	}

	public Page<BuyerDashboard> getAllBuyerDashboards(Specification<BuyerDashboard> spec, Pageable pageable) {
		return buyerDashboardDao.findAll(spec, pageable);
	}

	public ResponseEntity<BuyerDashboardPageDTO> getBuyerDashboards(BuyerDashboardSearchDTO buyerDashboardSearchDTO) {
	
			Integer buyerDashboardId = buyerDashboardSearchDTO.getBuyerDashboardId(); 
 			String sortBy = buyerDashboardSearchDTO.getSortBy();
			String sortOrder = buyerDashboardSearchDTO.getSortOrder();
			String searchQuery = buyerDashboardSearchDTO.getSearchQuery();
			Integer page = buyerDashboardSearchDTO.getPage();
			Integer size = buyerDashboardSearchDTO.getSize();

	        Specification<BuyerDashboard> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, buyerDashboardId, "buyerDashboardId"); 
			

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

		Page<BuyerDashboard> buyerDashboards = this.getAllBuyerDashboards(spec, pageable);
		
		//System.out.println(String.valueOf(buyerDashboards.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(buyerDashboards.getTotalPages()));
		
		List<BuyerDashboard> buyerDashboardsList = buyerDashboards.getContent();
		
		BuyerDashboardConvertCriteriaDTO convertCriteria = new BuyerDashboardConvertCriteriaDTO();
		List<BuyerDashboardDTO> buyerDashboardDTOs = this.convertBuyerDashboardsToBuyerDashboardDTOs(buyerDashboardsList,convertCriteria);
		
		BuyerDashboardPageDTO buyerDashboardPageDTO = new BuyerDashboardPageDTO();
		buyerDashboardPageDTO.setBuyerDashboards(buyerDashboardDTOs);
		buyerDashboardPageDTO.setTotalElements(buyerDashboards.getTotalElements());
		return ResponseEntity.ok(buyerDashboardPageDTO);
	}

	public List<BuyerDashboardDTO> convertBuyerDashboardsToBuyerDashboardDTOs(List<BuyerDashboard> buyerDashboards, BuyerDashboardConvertCriteriaDTO convertCriteria) {
		
		List<BuyerDashboardDTO> buyerDashboardDTOs = new ArrayList<BuyerDashboardDTO>();
		
		for (BuyerDashboard buyerDashboard : buyerDashboards) {
			buyerDashboardDTOs.add(convertBuyerDashboardToBuyerDashboardDTO(buyerDashboard,convertCriteria));
		}
		
		return buyerDashboardDTOs;

	}
	
	public BuyerDashboardDTO convertBuyerDashboardToBuyerDashboardDTO(BuyerDashboard buyerDashboard, BuyerDashboardConvertCriteriaDTO convertCriteria) {
		
		BuyerDashboardDTO buyerDashboardDTO = new BuyerDashboardDTO();
		
		buyerDashboardDTO.setBuyerDashboardId(buyerDashboard.getBuyerDashboardId());

	

		
		return buyerDashboardDTO;
	}

	public ResultDTO updateBuyerDashboard(BuyerDashboardDTO buyerDashboardDTO, RequestDTO requestDTO) {
		
		BuyerDashboard buyerDashboard = buyerDashboardDao.getById(buyerDashboardDTO.getBuyerDashboardId());

		buyerDashboard.setBuyerDashboardId(ControllerUtils.setValue(buyerDashboard.getBuyerDashboardId(), buyerDashboardDTO.getBuyerDashboardId()));



        buyerDashboard = buyerDashboardDao.save(buyerDashboard);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public BuyerDashboardDTO getBuyerDashboardDTOById(Integer buyerDashboardId) {
	
		BuyerDashboard buyerDashboard = buyerDashboardDao.getById(buyerDashboardId);
			
		
		BuyerDashboardConvertCriteriaDTO convertCriteria = new BuyerDashboardConvertCriteriaDTO();
		return(this.convertBuyerDashboardToBuyerDashboardDTO(buyerDashboard,convertCriteria));
	}







}
