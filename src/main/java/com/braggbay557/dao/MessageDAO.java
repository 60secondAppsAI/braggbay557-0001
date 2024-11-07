package com.braggbay557.dao;

import java.util.List;

import com.braggbay557.dao.GenericDAO;
import com.braggbay557.domain.Message;





public interface MessageDAO extends GenericDAO<Message, Integer> {
  
	List<Message> findAll();
	






}


