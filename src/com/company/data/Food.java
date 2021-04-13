package com.company.data;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class Food extends Product{

    private LocalDate bestBefore;


    public LocalDate getBestBefore() {
        return bestBefore;
    }

     Food(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        super(id, name, price, rating);
        this.bestBefore = bestBefore;
    }
    @Override
    public String toString(){
        return (super.toString()+" "+this.getBestBefore());
    }
    @Override
    public BigDecimal getDiscount(){
        return (getBestBefore().isEqual(LocalDate.now())?super.getDiscount():BigDecimal.ZERO);
    }
    public  Product applyRating(Rating newRating){
        return new Food(this.getId(), this.getName(), this.getPrice(), newRating,this.getBestBefore());}
}
