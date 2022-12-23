package esn.analyzer.priceanalyzer.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import esn.analyzer.priceanalyzer.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class AnalyzerResponse {
    @JsonProperty
    private List<Product> sortedProducts;
    @JsonProperty
    private List<Float> expensivenessPercentages;
    @JsonProperty
    private List<Float> otherProductPotentialValues;
}
