package com.braggbay557.dao;

import java.util.List;

import com.braggbay557.dao.GenericDAO;
import com.braggbay557.domain.ShoppingCart;





public interface ShoppingCartDAO extends GenericDAO<ShoppingCart, Integer> {
  
	List<ShoppingCart> findAll();
	






}


