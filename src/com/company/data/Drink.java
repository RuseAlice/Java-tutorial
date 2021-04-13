package com.company.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public final class Drink extends Product{

     Drink(int id, String name, BigDecimal price, Rating rating) {
        super(id, name, price, rating);
    }
  //  @Override
//    public BigDecimal getDiscount(){
//        if (LocalTime.now().isAfter(new LocalTime.of(17, 30))) {
//            return super.getDiscount();
//        }
//
//        return BigDecimal.ZERO;
//    }
  public LocalDate getBestBefore(){
         return LocalDate.now();
  }
  public  Product applyRating(Rating newRating){
      return new Drink(this.getId(), this.getName(), this.getPrice(), newRating);
}
}
