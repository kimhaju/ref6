package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.ProductDao;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {
	
	
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	public void setProductDao(ProductDao productdao) {
		this.productDao = productDao;
	}

	public ProductServiceImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addProduct(Product product) throws Exception {
		productDao.addProduct(product);
		
		/*
		System.out.println("1번째 insert");
		productDao.addProduct(product);
		
		System.out.println("2번째 insert");
		productDao.addProduct(product);
		
		System.out.println("트랜잭션 기능 확인::-> 값이 하나라도 저장되면 안됨. ");
		*/
		// 위의 트랜잭션 문제없이 작동되는 것을 확인. 값이 하나라도 들어가지 않았다. 

	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		
		return productDao.getProduct(prodNo);
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		List<Product> list=productDao.getProductList(search);
		int totalCount = productDao.getTotalCount(search);
		
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		
		productDao.updateProduct(product);

	}
	
	public int removeProduct(String prodName) throws Exception {
		
		return productDao.removeProduct(prodName);
	}

}
