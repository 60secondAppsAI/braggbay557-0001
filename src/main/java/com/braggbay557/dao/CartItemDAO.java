package com.braggbay557.dao;

import java.util.List;

import com.braggbay557.dao.GenericDAO;
import com.braggbay557.domain.CartItem;





public interface CartItemDAO extends GenericDAO<CartItem, Integer> {
  
	List<CartItem> findAll();
	






}


