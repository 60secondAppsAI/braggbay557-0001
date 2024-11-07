package com.braggbay557.dao;

import java.util.List;

import com.braggbay557.dao.GenericDAO;
import com.braggbay557.domain.Refund;





public interface RefundDAO extends GenericDAO<Refund, Integer> {
  
	List<Refund> findAll();
	






}


