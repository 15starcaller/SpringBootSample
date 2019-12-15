package com.cko.sampleSpringProject.controller;

import com.cko.sampleSpringProject.dao.ProductDAO;
import com.cko.sampleSpringProject.model.Film;
import com.cko.sampleSpringProject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductDAO productDAO;

    @GetMapping("/all")
    public  ModelAndView all(Product product){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/all");
        List<Product> productList = productDAO.findAll();
        modelAndView.addObject("products",productList);
        return modelAndView;
    }

    @GetMapping("/add")
    public String create() {
        return "product/add";
    }

    @PostMapping("/add")
    public RedirectView createProduct(Product product) {
        productDAO.save(product);
        return new RedirectView("/products/all");
    }

    @GetMapping("/edit")
    public ModelAndView ShowEditFilmsPage(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Product>  optional = productDAO.findById(id);
        Product product = optional.get();
        modelAndView.addObject("product",product);
        modelAndView.setViewName("product/edit");
        return modelAndView;
    }

    @PostMapping("/edit")
    public RedirectView edit(Product product) {
        productDAO.save(product);
        return new RedirectView("/products/all");
    }

    @GetMapping("/delete")
    public RedirectView delete(@RequestParam Long id) {
        Product product = productDAO.findProductById(id);
        product.setAmount(productDAO.findProductById(id).getAmount()-1);
        productDAO.save(product);
        return new RedirectView("/products/all");
    }

}
