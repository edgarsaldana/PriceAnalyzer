package esn.analyzer.priceanalyzer;

import esn.analyzer.priceanalyzer.domain.AnalyzerResponse;
import esn.analyzer.priceanalyzer.service.AnalyzeService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PriceAnalyzerApplicationTests {

    @Test
    void whenAnalyzeTwoProductsThenReturnCheapestProductExpensivenessPercentageAndPotentialValue(){

        var product1 = new Product("Crema", "Nivea", 100, 100);
        var product2 = new Product("Crema", "Teatrical", 100, 70);

        List<Product> products = getProducts(product1, product2);

        AnalyzeService service = new AnalyzeService();
        service.setProducts(products);

        var analyzerResponseExpected = new AnalyzerResponse(List.of(product2, product1), List.of(0f, 42.86f), List.of(0f, 70f));

        var analyzerResponse = service.analyze();

        assertThat(analyzerResponse)
                .usingRecursiveComparison()
                .isEqualTo(analyzerResponseExpected);
    }

    @Test
    void whenAnalyzeTwoProductsWithSameCostThenReturnProperResponse(){

        var product1 = new Product("Crema", "Nivea", 50, 25);
        var product2 = new Product("Crema", "Teatrical", 100, 50);

        List<Product> products = getProducts(product1, product2);

        AnalyzeService service = new AnalyzeService();
        service.setProducts(products);

        var analyzerResponseExpected = new AnalyzerResponse(List.of(product1, product2), List.of(0f, 0f), List.of(0f, 0f));

        var analyzerResponse = service.analyze();

        assertThat(analyzerResponse)
                .usingRecursiveComparison()
                .isEqualTo(analyzerResponseExpected);
    }

    @Test
    void whenAnalyzeThreeProductsThenReturnProperResponse(){

        var product1 = new Product("Crema", "Nivea", 100, 100);
        var product2 = new Product("Crema", "Teatrical", 100, 70);
        var product3 = new Product("Crema", "Teatrical 2", 100, 50);

        List<Product> products = getProducts(product1, product2, product3);

        AnalyzeService service = new AnalyzeService();
        service.setProducts(products);

        List<Float> expensivenessPercentagesExpected = getExpensivenessPercentagesExpected();

        List<Float> otherPotentialValuesExpected = getOtherPotentialValuesExpected();

        var analyzerResponseExpected = new AnalyzerResponse(List.of(product3, product2, product1), expensivenessPercentagesExpected, otherPotentialValuesExpected);

        var analyzerResponse = service.analyze();

        assertThat(analyzerResponse)
                .usingRecursiveComparison()
                .isEqualTo(analyzerResponseExpected);
    }

    private List<Product> getProducts(Product product1, Product product2) {
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        return products;
    }

    private List<Product> getProducts(Product product1, Product product2, Product product3) {
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        return products;
    }

    private List<Float> getOtherPotentialValuesExpected() {
        List<Float> otherPotentialValuesExpected = new ArrayList<>();
        otherPotentialValuesExpected.add(0f);
        otherPotentialValuesExpected.add(50f);
        otherPotentialValuesExpected.add(50f);
        return otherPotentialValuesExpected;
    }

    private List<Float> getExpensivenessPercentagesExpected() {
        List<Float> expensivenessPercentagesExpected = new ArrayList<>();
        expensivenessPercentagesExpected.add(0f);
        expensivenessPercentagesExpected.add(40f);
        expensivenessPercentagesExpected.add(100f);
        return expensivenessPercentagesExpected;
    }


}
