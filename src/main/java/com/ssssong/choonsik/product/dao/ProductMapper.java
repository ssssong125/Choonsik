//package com.ssssong.choonsik.product.dao;
//
//import com.ssssong.choonsik.common.paging.SelectCriteria;
//import com.ssssong.choonsik.product.dto.ProductDTO;
//import org.apache.ibatis.annotations.Mapper;
//
//import java.util.List;
//
//@Mapper
//public interface ProductMapper {
//
//    ProductDTO selectProduct(String productCode);
//
//    List<ProductDTO> selectProductListAboutMeal();
//
//    List<ProductDTO> selectProductListAboutDessert();
//
//    List<ProductDTO> selectProductListAboutBeverage();
//
//
//    int insertProduct(ProductDTO product);
//
//    int updateProduct(ProductDTO product);
//
//    List<ProductDTO> productListWithSearchValue(String search);
//
//    ProductDTO selectProductForAdmin(String productCode);
//
//    int selectProductTotal();
//
//    List<ProductDTO> selectProductListWithPaging(SelectCriteria selectCriteria);
//
//    int selectProductTotalForAdmin();
//
//    List<ProductDTO> selectProductListWithPagingForAdmin(SelectCriteria selectCriteria);
//}
