package esn.analyzer.priceanalyzer.controller;

import esn.analyzer.priceanalyzer.Product;
import esn.analyzer.priceanalyzer.domain.AnalyzeProductsDTO;
import esn.analyzer.priceanalyzer.domain.AnalyzerResponse;
import esn.analyzer.priceanalyzer.service.AnalyzeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
public class IndexController {

    private final AnalyzeService analyzeService;

    public IndexController(AnalyzeService analyzeService) {
        this.analyzeService = analyzeService;
    }

    @GetMapping("/")
    public ModelAndView index(){
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("title", "Price Analyzer");
        modelAndView.addObject("msg", "Price Analyzer");

        AnalyzeProductsDTO productsForm = new AnalyzeProductsDTO();
        var product = new Product();

        productsForm.addProduct(product);
        productsForm.addProduct(new Product());

        modelAndView.addObject("form", productsForm);

        return modelAndView;
    }

    @PostMapping("/analyze")
    public ModelAndView analyze(@ModelAttribute AnalyzeProductsDTO form){

        log.info("Post operation");

        var modelAndView = new ModelAndView();

        log.info("Form received: {}", form);

        analyzeService.setProducts(form.getProducts());
        var response = analyzeService.analyze();
        var sortedProducts = response.getSortedProducts();
        form.setProducts(sortedProducts);
        log.info("Sorted products: {}", sortedProducts);

        modelAndView.setViewName("index");
        modelAndView.addObject("title", "Price Analyzer");
        modelAndView.addObject("msg", "Price Analyzer");
        modelAndView.addObject("form", form);
        return modelAndView;
    }
}
