package com.braggbay557.dao;

import java.util.List;

import com.braggbay557.dao.GenericDAO;
import com.braggbay557.domain.WatchlistItem;





public interface WatchlistItemDAO extends GenericDAO<WatchlistItem, Integer> {
  
	List<WatchlistItem> findAll();
	






}


