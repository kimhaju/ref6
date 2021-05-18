package com.model2.mvc.service.product.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.model2.mvc.service.product.ProductService;

import junit.framework.Assert;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		                                "classpath:config/context-aspect.xml",
		                                "classpath:config/context-mybatis.xml",
		                                "classpath:config/context-transaction.xml" })
public class ProductServiceTest {
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	@Test
	public void testAddProduct() throws Exception{
		
		Product product = new Product();
		product.setProdName("testProduct");
		product.setProdDetail("test");
		product.setManuDate("210503".trim());
		product.setPrice(1000);
		product.setFileName("x");
		
		System.out.println("상품 추가 완료  : "+product);
		
		productService.addProduct(product);
		
		//int prodNo = Integer.parseInt("prodNo");
		
		product = productService.getProduct(10042);
		
		System.out.println(product);
		
		Assert.assertEquals("testProduct", product.getProdName());
		Assert.assertEquals("x", product.getFileName());
		Assert.assertEquals("210503", product.getManuDate());
		
	}
	//@Test
	public void testGetProduct() throws Exception{
		
		Product product = new Product();
		
		product = productService.getProduct(10010);
		
		System.out.println(product);
		
		Assert.assertEquals("testProduct", product.getProdName());
		
	}
	//@Test
	public void testUpdateProduct() throws Exception{
		
		Product product = productService.getProduct(10010);
		
		product.setProdName("changeProduct");
		product.setProdDetail("change");
		product.setManuDate("210506");
		product.setPrice(500);
		product.setFileName("new");
		
		
		System.out.println("update Product 상품 업데이트   : "+product);
		
		Assert.assertNotNull(product);
	
	}
	//@Test
	public void testGetProductListAll() throws Exception{
		
		Search search = new Search();
		
		search.setCurrentPage(1);
		search.setPageSize(3);
		
		Map<String,Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
	 	//Assert.assertEquals(3, list.size());
	 	
		System.out.println("product 상품 리스트 : "+list);
		
		Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
		
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	
	 	System.out.println("product search: "+search);
	 	//Assert.assertEquals(3, list.size());
	 	
	 	System.out.println("product List 상품 리스트 조회 : "+list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	
	}
	//@Test
	public void testRemoveProduct() throws Exception{
		
		int remove = productService.removeProduct("testProduct");
		
		System.out.println("상품 삭제 값: "+remove);
	}
}
