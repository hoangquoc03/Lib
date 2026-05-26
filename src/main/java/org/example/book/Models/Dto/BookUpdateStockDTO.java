package org.example.book.Models.Dto;
import jakarta.validation.constraints.Min;

public class BookUpdateStockDTO {
    @Min(value = 0, message = "Stock phải lớn hơn hoặc bằng 0")
    private Integer stock;

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
