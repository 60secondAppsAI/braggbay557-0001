package com.braggbay557.dao;

import java.util.List;

import com.braggbay557.dao.GenericDAO;
import com.braggbay557.domain.Address;





public interface AddressDAO extends GenericDAO<Address, Integer> {
  
	List<Address> findAll();
	






}


