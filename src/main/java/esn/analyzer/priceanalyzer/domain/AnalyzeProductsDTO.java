package esn.analyzer.priceanalyzer.domain;

import esn.analyzer.priceanalyzer.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnalyzeProductsDTO {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        this.products.add(product);
    }
}
