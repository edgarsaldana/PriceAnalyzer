package esn.analyzer.priceanalyzer.controller;

import esn.analyzer.priceanalyzer.Product;
import esn.analyzer.priceanalyzer.domain.AnalyzerResponse;
import esn.analyzer.priceanalyzer.service.AnalyzeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnalyzeRESTController {

    private final AnalyzeService analyzeService;

    public AnalyzeRESTController(AnalyzeService analyzeService) {
        this.analyzeService = analyzeService;
    }

    @PostMapping(value = "analyze",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public AnalyzerResponse analyze(@RequestBody List<Product> products){
        analyzeService.setProducts(products);
        return analyzeService.analyze();
    }
}
