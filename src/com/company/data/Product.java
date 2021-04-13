package com.company.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

import com.company.data.Rating;

public abstract class Product implements Ratable<Product>{

    private int id;
    private String name;
    private BigDecimal price;
    private Rating rating;
    public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public BigDecimal getDiscount(){
        return price.multiply(DISCOUNT_RATE).setScale(2, RoundingMode.HALF_UP);
    }
    public Rating getRating() {
        return rating;
    }

//     Product(){
//        this(0,"no name", BigDecimal.valueOf(0));
//    }
    Product(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }
     Product(int id, String name, BigDecimal price) {
        this(id, name, price,Rating.NOT_RATED);
    }
    public abstract LocalDate getBestBefore();
    //public abstract Product applyRating(Rating newRating);



    @Override
    public String toString(){
        return getId()+" "+getName()+" "+getPrice()+" "+getDiscount()+" "+getRating().getStars();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        //if (o == null || getClass() != o.getClass()) return false;
        if(o instanceof Product)
            return true;
        Product product = (Product) o;
        return id == product.id; //&& Objects.equals(name, product.name) && Objects.equals(price, product.price) && rating == product.rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, rating);
    }
}
