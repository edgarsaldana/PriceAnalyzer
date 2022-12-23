package esn.analyzer.priceanalyzer.service;

import esn.analyzer.priceanalyzer.Product;
import esn.analyzer.priceanalyzer.domain.AnalyzerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class AnalyzeService {

    private List<Product> products;
    private final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public void setProducts(List<Product> products) {
        if (products.size() < 2){
            throw new IllegalArgumentException("Required at least two products");
        }
        log.info("Products will be sorted: {}", products);
        Collections.sort(products);
        this.products = products;
    }

    private List<Float> getExpensivenessPercentages() {
        var list = new ArrayList<Float>();
        list.add(0f);

        for(int i = 0; i < products.size() - 1; i++) {
            list.add(roundToTwoDecimals(calcExpensiveness(i)));
        }

        return list;
    }

    private float roundToTwoDecimals(float number) {
        return Float.parseFloat(decimalFormat.format(number));
    }

    private float calcExpensiveness(int i) {
        return (products.get(i + 1).getPricePerUnit() * 100) / products.get(0).getPricePerUnit() - 100;
    }

    private List<Float> getPotentialValues() {
        var list = new ArrayList<Float>();
        list.add(0f);

        for(int i = 0; i < products.size() - 1; i++) {
            var cheapestProduct = products.get(0);
            var moreExpensiveProduct = products.get(i+1);

            if (cheapestProduct.getPricePerUnit() == moreExpensiveProduct.getPricePerUnit()){
                list.add(0f);
                continue;
            }

            list.add(roundToTwoDecimals(cheapestProduct.getPricePerUnit() * moreExpensiveProduct.getContent()));
        }

        return list;
    }

    public AnalyzerResponse analyze() {

        var sortedProducts = products;

        var cheaplyPercentages = getExpensivenessPercentages();
        var otherProductPotentialValues = getPotentialValues();

        return new AnalyzerResponse(sortedProducts, cheaplyPercentages, otherProductPotentialValues);
    }
}
