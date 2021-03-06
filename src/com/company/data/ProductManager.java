package com.company.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProductManager {

    private Map<Product, List<Review>> products = new HashMap<>();
    private ResourceFormatter formatter;
    private ResourceBundle config = ResourceBundle.getBundle("com.company.data.config");
    private MessageFormat reviewFormat = new MessageFormat(config.getString("review.data.format"));
    private MessageFormat productFormat = new MessageFormat(config.getString("product.data.format"));

    private static Map<String, ResourceFormatter> formatters=
            Map.of("en-GB", new ResourceFormatter(Locale.UK),
                    "en-US", new ResourceFormatter(Locale.US),
                    "fr-FR",new ResourceFormatter(Locale.FRANCE),
                    "ru-RU",new ResourceFormatter(new Locale("ru", "RU")),
                    "zh-CN",new ResourceFormatter(Locale.CHINA));
//    private Product product;
//    private Review[] reviews=new Review[5];
    private static final Logger logger = Logger.getLogger(ProductManager.class.getName());

    public ProductManager(Locale locale) {
        this(locale.toLanguageTag());
    }
    public ProductManager(String languageTag) {
        changeLocale(languageTag);
    }

    public void changeLocale(String language){
        formatter=formatters.getOrDefault(language , formatters.get("en-GB"));
    }
    public static Set<String> getSupportedLocales(){
        return formatters.keySet();
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore){
        Product product = new Food(id, name, price, rating, bestBefore);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }
    public Product createProduct(int id, String name, BigDecimal price, Rating rating){
        Product product = new Drink(id, name, price, rating);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }
    public Product reviewProduct(int id, Rating rating,String comments ){
        try {
            return reviewProduct(findProduct(id), rating, comments);
        } catch (ProductManagerException e) {
            //e.printStackTrace();
            logger.log(Level.INFO, e.getMessage());
        }
        return null;
    }

    public Product reviewProduct(Product product, Rating rating, String comments){
//        if(reviews[reviews.length-1]!=null){
//            reviews = Arrays.copyOf(reviews, reviews.length+5);
//        }
        List<Review> reviews = products.get(product);
        reviews.add(new Review(rating, comments));
//        int sum=0;
//        for(Review review:reviews){
//            sum+=review.getRating().ordinal();
//        }
//        int sum=0,i=0;
//        boolean reviewed = false;
//        while(i< reviews.length && !reviewed){
//            if(reviews[i]==null){
//                reviews[i]=new Review(rating,comments);
//                reviewed = true;
//            }
//            sum+=reviews[i].getRating().ordinal();
//            i++;
//        }
     //   product=product.applyRating(Ratable.convert(Math.round((float)sum/reviews.size())));

        product=product.applyRating(Ratable.convert(
                (int)Math.round(
                        reviews.stream()
                            .mapToInt(r->r.getRating().ordinal())
                            .average()
                            .orElse(0))));

        products.put(product,reviews);
        return product;
    }

    public void printProductReport(int id){
        try {
            printProductReport(findProduct(id));
        } catch (ProductManagerException e) {
//            e.printStackTrace();
           logger.log(Level.INFO, e.getMessage());
        }
    }

    public void printProductReport(Product product){
        List<Review> reviews = products.get(product);
        StringBuilder txt = new StringBuilder();
        txt.append(formatter.formatProduct(product));
        txt.append('\n');
        Collections.sort(reviews);
        if(reviews.isEmpty()){
            txt.append(formatter.getText("no.reviews")+"\n");
        }else{
            txt.append(reviews.stream().map(r->formatter.formatReview(r)+'\n').collect(Collectors.joining()));
        }
//        for (Review review:reviews) {
//            if(review==null){
//                break;
//            }
//            txt.append(formatter.formatReview(review));
//            txt.append('\n');
//        }
//        if(reviews.isEmpty()) {
//            txt.append(formatter.getText("no.reviews"));
//            txt.append('\n');
//        }
        System.out.println(txt);
    }

    public void printProducts(Predicate<Product> filter, Comparator<Product>sorter){
//        List<Product> productList = new ArrayList<>(products.keySet());
//        productList.sort(sorter);
        StringBuilder txt = new StringBuilder();

//        for(Product product:productList){
//            txt.append(formatter.formatProduct(product));
//            txt.append("\n");
//        }
        products.keySet()
                .stream()
                .sorted(sorter)
                .filter(filter)
                .forEach(p->txt.append(formatter.formatProduct(p)+'\n'));
        System.out.println(txt);
    }

    public void parseReview(String text){
        try {
            Object[] values= reviewFormat.parse(text);
            reviewProduct(Integer.parseInt((String)values[0]),
                    Ratable.convert(Integer.parseInt((String)values[1])),(String) values[2]);
        } catch (ParseException |NumberFormatException e) {
            logger.log(Level.WARNING, "Error parsing review "+text);
        }
    }
    public void parseProduct(String text){
        try {
            Object[] values= productFormat.parse(text);
            int id = Integer.parseInt((String) values[1]);
            String name = (String) values[2];
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble((String) values[3]));
            Rating rating = Ratable.convert(Integer.parseInt((String) values[4]));
            switch ((String) values[0]){
                case "D":
                    createProduct(id, name, price, rating);
                    break;
                case "F":
                    LocalDate bestBefore = LocalDate.parse((String)values[5]);
                    createProduct(id, name, price, rating, bestBefore);
            }
        } catch (ParseException |NumberFormatException| DateTimeParseException e) {
            logger.log(Level.WARNING, "Error parsing product "+text+" "+e.getMessage());
        }
    }

    public Product findProduct(int id) throws ProductManagerException{
//        Product result=null;
//        for(Product product:products.keySet()){
//            if(product.getId()==id){
//                result=product;
//                break;
//            }
//
//        }
//        return result;
        return products.keySet().stream().filter(p->p.getId()==id).findFirst()
                .orElseThrow(()->new ProductManagerException("Product with id "+id+" not found"));//get();//orElseGet(()->null);
        //get arunca o eroare no such element
    }



//static nested class
    private static class ResourceFormatter{

        private Locale locale;
        private ResourceBundle resources;
        private DateTimeFormatter dateFormat;
        private NumberFormat moneyFormat;

        private ResourceFormatter(Locale locale){
            this.locale = locale;
            resources = ResourceBundle.getBundle("com.company.data.resources", locale);
            dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
            moneyFormat = NumberFormat.getCurrencyInstance(locale);
        }
        private String formatProduct(Product product){
            return MessageFormat.format(resources.getString("product"),
                    product.getName(),
                    moneyFormat.format(product.getPrice()),
                    product.getRating().getStars(),
                    dateFormat.format(product.getBestBefore()));
        }
        private String formatReview(Review review){
            return MessageFormat.format(resources.getString("review"),
                    review.getRating().getStars(),
                    review.getComments());
        }
        private String getText(String key){
            return resources.getString(key);
        }


    }
    public Map<String, String> getDiscounts(){
        return products.keySet()
                .stream()
                .collect(Collectors.groupingBy(
                        product->product.getRating().getStars(),
                        Collectors.collectingAndThen(
                                Collectors.summingDouble(product->product.getDiscount().doubleValue()),
                        discount->formatter.moneyFormat.format(discount))));
    }
}
