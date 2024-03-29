package mft.controller;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Products;
import mft.model.service.ProductService;

import java.util.List;
import java.util.regex.Pattern;

@Log4j
public class ProductController {

    private static ProductController controller=new ProductController();

    private ProductController() {
    }

    public static ProductController getController() {
        return controller;
    }

    public Products save(String name, String brand, String size, Double price , String description) throws Exception {
            Products products =
                    Products
                            .builder()
                            .name(name)
                            .brand(brand)
                            .size(size)
                            .price(price)
                            .description(description)
                            .build();
            ProductService.getService().save(products);
            log.info("Save Product");
            return products;
    }

    public Products edit(Integer id, String name, String brand, String size, Double price , String description) throws Exception {
            Products products =
                    Products
                            .builder()
                            .id(id)
                            .name(name)
                            .brand(brand)
                            .size(size)
                            .price(price)
                            .description(description)
                            .build();
            ProductService.getService().edit(products);
            log.info("Edit Product");
            return products;
    }

    public Products remove(Integer id) throws Exception {
        Products products=ProductService.getService().findById(id);
        ProductService.getService().remove(id);
        log.info("Remove Product");
        return products;
    }

    public List<Products> findAll() throws Exception {
        log.info("FindAll Product");
        return ProductService.getService().findAll();
    }

    public List<Products> findByAll(String searchText) throws Exception {
        log.info("FindByAll Product");
        return ProductService.getService().findByAll(searchText);
    }

    public Products findById(Integer id) throws Exception {
        log.info("FindById Product");
        return ProductService.getService().findById(id);
    }
}


