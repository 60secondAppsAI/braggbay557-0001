package com.braggbay557.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay557.domain.ShoppingCart;
import com.braggbay557.dto.ShoppingCartDTO;
import com.braggbay557.dto.ShoppingCartSearchDTO;
import com.braggbay557.dto.ShoppingCartPageDTO;
import com.braggbay557.dto.ShoppingCartConvertCriteriaDTO;
import com.braggbay557.service.GenericService;
import com.braggbay557.dto.common.RequestDTO;
import com.braggbay557.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ShoppingCartService extends GenericService<ShoppingCart, Integer> {

	List<ShoppingCart> findAll();

	ResultDTO addShoppingCart(ShoppingCartDTO shoppingCartDTO, RequestDTO requestDTO);

	ResultDTO updateShoppingCart(ShoppingCartDTO shoppingCartDTO, RequestDTO requestDTO);

    Page<ShoppingCart> getAllShoppingCarts(Pageable pageable);

    Page<ShoppingCart> getAllShoppingCarts(Specification<ShoppingCart> spec, Pageable pageable);

	ResponseEntity<ShoppingCartPageDTO> getShoppingCarts(ShoppingCartSearchDTO shoppingCartSearchDTO);
	
	List<ShoppingCartDTO> convertShoppingCartsToShoppingCartDTOs(List<ShoppingCart> shoppingCarts, ShoppingCartConvertCriteriaDTO convertCriteria);

	ShoppingCartDTO getShoppingCartDTOById(Integer shoppingCartId);







}





