package com.company.app;

import com.company.data.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Locale;

public class Shop {

    public static void main(String[] args) {

        ProductManager pm = new ProductManager("en-GB");

       // pm.createProduct(101,"Tea", BigDecimal.valueOf(1.99),Rating.NOT_RATED);
        pm.parseProduct("D,101,Tea,1.99,0,2020-04-16");
//        pm.parseProduct("D,101,Tea,1.99,0");
        pm.printProductReport(101);
       // pm.reviewProduct(101,Rating.FOUR_STAR, "Nice hot cup of tea");
//        pm.parseReview("101,4,Nice hot cup of tea");
//        pm.printProductReport(101);

        //pm.changeLocale("ru-RU");
//        pm.reviewProduct(101,Rating.TWO_STAR, "Weak tea");
//        pm.reviewProduct(101,Rating.TWO_STAR, "Bad tea");
//        pm.reviewProduct(101,Rating.FIVE_STAR, "Great tea");
//        pm.reviewProduct(101,Rating.ONE_STAR, "Bleah cup of tea");
//        pm.printProductReport(101);
        pm.parseReview("101,4,Nice hot cup of tea");
        pm.parseReview("101,2,Bad tea");
        pm.parseReview("101,5,Great tea");
        pm.parseReview("101,1,Bleah tea");
        pm.printProductReport(101);

        pm.parseProduct("F,103,Cake,1.99,0,2020-04-16");
        pm.printProductReport(103);
       //Product p2 =
//               pm.createProduct(102,"Coffee", BigDecimal.valueOf(5.99), Rating.FOUR_STAR);
//        pm.reviewProduct(102,Rating.ONE_STAR, "Bleah cup of coffee");
//        //pm.printProductReport(p2);
//
//
//
//        Product p3 = pm.createProduct(103,"Cake", BigDecimal.valueOf(12.50), Rating.FIVE_STAR, LocalDate.now().plusDays(2));
//        p3=pm.reviewProduct(p3,Rating.TWO_STAR, "Bad ");
//        p3=pm.reviewProduct(p3,Rating.FIVE_STAR, "Great ");
//        p3=pm.reviewProduct(p3,Rating.ONE_STAR, "Bleah ");
//        //pm.printProductReport(p3);
//
//        Product p4 = pm.createProduct(105, "Cookie", BigDecimal.valueOf(5.99),Rating.ONE_STAR,LocalDate.now().plusDays(4));
//        p4=pm.reviewProduct(p4,Rating.TWO_STAR, "Bad ");
//        p4=pm.reviewProduct(p4,Rating.FIVE_STAR, "Great ");
//        p4=pm.reviewProduct(p4,Rating.ONE_STAR, "Bleah ");
//        //pm.printProductReport(p4);
//
//        Product p6 = pm.createProduct(104,"Chocolate",BigDecimal.valueOf(2.99), Rating.FIVE_STAR);
//        p6=pm.reviewProduct(p6,Rating.TWO_STAR, "Bad ");
//        p6=pm.reviewProduct(p6,Rating.FIVE_STAR, "Great ");
//        p6=pm.reviewProduct(p6,Rating.ONE_STAR, "Bleah ");
//        pm.printProductReport(p6);
//
//        Comparator<Product> ratingSorter = (p1, p2)->p2.getRating().ordinal()-p1.getRating().ordinal();
//        Comparator<Product> priceSorter = (p1, p2)->p2.getPrice().compareTo(p1.getPrice());
//        //pm.printProducts(ratingSorter);
//
//        pm.printProducts(p->p.getPrice().floatValue()<2, (p1,p2)->p2.getRating().ordinal()-p1.getRating().ordinal());
//        pm.printProducts(p->p.getPrice().floatValue()<2, (p1,p2)->p2.getPrice().compareTo(p1.getPrice()));
//
//        pm.getDiscounts().forEach((rating, discount)-> System.out.println(rating+'\t'+discount));


//        pm.printProducts(ratingSorter.thenComparing(priceSorter));
//        pm.printProducts(ratingSorter.thenComparing(priceSorter).reversed());

//        Product p7 = pm.createProduct(104,"Chocolate",BigDecimal.valueOf(2.99), Rating.FIVE_STAR, LocalDate.now().plusDays(2));
//        Product p8 = p4.applyRating(Rating.ONE_STAR);
//        Product p9 = p6.applyRating(Rating.TWO_STAR);
//
//        System.out.println(p6.equals(p7));//fals->compara referintele
//        if(p3 instanceof Food){
//            LocalDate bestBef=((Food) p3).getBestBefore();
//        }


//        System.out.println(p1.getId()+" "+p1.getName()+" "+p1.getPrice()+" "+p1.getDiscount() +" "+p1.getRating().getStars());
//        System.out.println(p2.getId()+" "+p2.getName()+" "+p2.getPrice()+" "+p2.getDiscount()+" "+p2.getRating().getStars());
//        System.out.println(p3.getId()+" "+p3.getName()+" "+p3.getPrice()+" "+p3.getDiscount()+" "+p3.getRating().getStars());
//        System.out.println(p4.getId()+" "+p4.getName()+" "+p4.getPrice()+" "+p4.getDiscount()+" "+p4.getRating().getStars());
//        System.out.println(p5.getId()+" "+p5.getName()+" "+p5.getPrice()+" "+p5.getDiscount()+" "+p5.getRating().getStars());

//        System.out.println(p1);
//        System.out.println(p2);
//        System.out.println(p3);
//        System.out.println(p4);
//        System.out.println(p5);
//        System.out.println(p6);
//        System.out.println(p7);
//        System.out.println(p8);
//        System.out.println(p9);

    }
}
