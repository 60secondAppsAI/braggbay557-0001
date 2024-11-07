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
import com.braggbay557.dao.SellerDashboardDAO;
import com.braggbay557.domain.SellerDashboard;
import com.braggbay557.dto.SellerDashboardDTO;
import com.braggbay557.dto.SellerDashboardSearchDTO;
import com.braggbay557.dto.SellerDashboardPageDTO;
import com.braggbay557.dto.SellerDashboardConvertCriteriaDTO;
import com.braggbay557.dto.common.RequestDTO;
import com.braggbay557.dto.common.ResultDTO;
import com.braggbay557.service.SellerDashboardService;
import com.braggbay557.util.ControllerUtils;





@Service
public class SellerDashboardServiceImpl extends GenericServiceImpl<SellerDashboard, Integer> implements SellerDashboardService {

    private final static Logger logger = LoggerFactory.getLogger(SellerDashboardServiceImpl.class);

	@Autowired
	SellerDashboardDAO sellerDashboardDao;

	


	@Override
	public GenericDAO<SellerDashboard, Integer> getDAO() {
		return (GenericDAO<SellerDashboard, Integer>) sellerDashboardDao;
	}
	
	public List<SellerDashboard> findAll () {
		List<SellerDashboard> sellerDashboards = sellerDashboardDao.findAll();
		
		return sellerDashboards;	
		
	}

	public ResultDTO addSellerDashboard(SellerDashboardDTO sellerDashboardDTO, RequestDTO requestDTO) {

		SellerDashboard sellerDashboard = new SellerDashboard();

		sellerDashboard.setSellerDashboardId(sellerDashboardDTO.getSellerDashboardId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		sellerDashboard = sellerDashboardDao.save(sellerDashboard);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<SellerDashboard> getAllSellerDashboards(Pageable pageable) {
		return sellerDashboardDao.findAll(pageable);
	}

	public Page<SellerDashboard> getAllSellerDashboards(Specification<SellerDashboard> spec, Pageable pageable) {
		return sellerDashboardDao.findAll(spec, pageable);
	}

	public ResponseEntity<SellerDashboardPageDTO> getSellerDashboards(SellerDashboardSearchDTO sellerDashboardSearchDTO) {
	
			Integer sellerDashboardId = sellerDashboardSearchDTO.getSellerDashboardId(); 
 			String sortBy = sellerDashboardSearchDTO.getSortBy();
			String sortOrder = sellerDashboardSearchDTO.getSortOrder();
			String searchQuery = sellerDashboardSearchDTO.getSearchQuery();
			Integer page = sellerDashboardSearchDTO.getPage();
			Integer size = sellerDashboardSearchDTO.getSize();

	        Specification<SellerDashboard> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, sellerDashboardId, "sellerDashboardId"); 
			

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

		Page<SellerDashboard> sellerDashboards = this.getAllSellerDashboards(spec, pageable);
		
		//System.out.println(String.valueOf(sellerDashboards.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(sellerDashboards.getTotalPages()));
		
		List<SellerDashboard> sellerDashboardsList = sellerDashboards.getContent();
		
		SellerDashboardConvertCriteriaDTO convertCriteria = new SellerDashboardConvertCriteriaDTO();
		List<SellerDashboardDTO> sellerDashboardDTOs = this.convertSellerDashboardsToSellerDashboardDTOs(sellerDashboardsList,convertCriteria);
		
		SellerDashboardPageDTO sellerDashboardPageDTO = new SellerDashboardPageDTO();
		sellerDashboardPageDTO.setSellerDashboards(sellerDashboardDTOs);
		sellerDashboardPageDTO.setTotalElements(sellerDashboards.getTotalElements());
		return ResponseEntity.ok(sellerDashboardPageDTO);
	}

	public List<SellerDashboardDTO> convertSellerDashboardsToSellerDashboardDTOs(List<SellerDashboard> sellerDashboards, SellerDashboardConvertCriteriaDTO convertCriteria) {
		
		List<SellerDashboardDTO> sellerDashboardDTOs = new ArrayList<SellerDashboardDTO>();
		
		for (SellerDashboard sellerDashboard : sellerDashboards) {
			sellerDashboardDTOs.add(convertSellerDashboardToSellerDashboardDTO(sellerDashboard,convertCriteria));
		}
		
		return sellerDashboardDTOs;

	}
	
	public SellerDashboardDTO convertSellerDashboardToSellerDashboardDTO(SellerDashboard sellerDashboard, SellerDashboardConvertCriteriaDTO convertCriteria) {
		
		SellerDashboardDTO sellerDashboardDTO = new SellerDashboardDTO();
		
		sellerDashboardDTO.setSellerDashboardId(sellerDashboard.getSellerDashboardId());

	

		
		return sellerDashboardDTO;
	}

	public ResultDTO updateSellerDashboard(SellerDashboardDTO sellerDashboardDTO, RequestDTO requestDTO) {
		
		SellerDashboard sellerDashboard = sellerDashboardDao.getById(sellerDashboardDTO.getSellerDashboardId());

		sellerDashboard.setSellerDashboardId(ControllerUtils.setValue(sellerDashboard.getSellerDashboardId(), sellerDashboardDTO.getSellerDashboardId()));



        sellerDashboard = sellerDashboardDao.save(sellerDashboard);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SellerDashboardDTO getSellerDashboardDTOById(Integer sellerDashboardId) {
	
		SellerDashboard sellerDashboard = sellerDashboardDao.getById(sellerDashboardId);
			
		
		SellerDashboardConvertCriteriaDTO convertCriteria = new SellerDashboardConvertCriteriaDTO();
		return(this.convertSellerDashboardToSellerDashboardDTO(sellerDashboard,convertCriteria));
	}







}
