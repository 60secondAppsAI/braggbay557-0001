package com.braggbay557.dao;

import java.util.List;

import com.braggbay557.dao.GenericDAO;
import com.braggbay557.domain.BuyerDashboard;





public interface BuyerDashboardDAO extends GenericDAO<BuyerDashboard, Integer> {
  
	List<BuyerDashboard> findAll();
	






}


