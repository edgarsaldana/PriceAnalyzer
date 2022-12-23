package esn.analyzer.priceanalyzer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.NumberFormat;

import java.text.DecimalFormat;

@Data
@NoArgsConstructor
@Slf4j
public class Product implements Comparable<Product>{

    @JsonProperty
    private String name;
    @JsonProperty
    private String brand;
    @NotEmpty
    @NumberFormat
    @JsonProperty
    private int content;

    public void setContent(int content) {
        if (content > 0){
            this.pricePerUnit = price/content;
        }
        this.content = content;
    }

    public void setPrice(float price) {
        if (content > 0){
            this.pricePerUnit = price/content;
        }
        this.price = price;
    }

    @NotEmpty
    @NumberFormat
    @JsonProperty
    private float price;

    private float pricePerUnit;

    @JsonCreator
    public Product(String name, String brand, int content, float price) {
        this.name = name;
        this.brand = brand;
        this.content = content;
        this.price = price;
        this.pricePerUnit = roundToTwoDecimals(price/content);
    }

    @Override
    public int compareTo(Product product) {
        return Float.compare(this.pricePerUnit, product.pricePerUnit);
    }

    private float roundToTwoDecimals(float number) {
        log.info("Number that will be rounded: {}", number);
        return Float.parseFloat(new DecimalFormat("0.00").format(number));
    }
}
