package com.braggbay557.dao;

import java.util.List;

import com.braggbay557.dao.GenericDAO;
import com.braggbay557.domain.SellerDashboard;





public interface SellerDashboardDAO extends GenericDAO<SellerDashboard, Integer> {
  
	List<SellerDashboard> findAll();
	






}


