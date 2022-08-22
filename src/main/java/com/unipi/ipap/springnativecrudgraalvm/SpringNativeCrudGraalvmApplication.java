package com.unipi.ipap.springnativecrudgraalvm;

import com.unipi.ipap.springnativecrudgraalvm.entity.*;
import com.unipi.ipap.springnativecrudgraalvm.service.CartService;
import com.unipi.ipap.springnativecrudgraalvm.service.CustomerService;
import com.unipi.ipap.springnativecrudgraalvm.service.ItemService;
import com.unipi.ipap.springnativecrudgraalvm.service.ProductService;
import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.nativex.hint.TypeHint;

import javax.persistence.EntityExistsException;
import java.util.*;
import java.util.stream.IntStream;

@SpringBootApplication
@TypeHint(types = MySQL5Dialect.class, typeNames = "org.hibernate.dialect.MySQL5Dialect")
public class SpringNativeCrudGraalvmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringNativeCrudGraalvmApplication.class, args);
    }

    @Bean
    @Profile("off")
    public ApplicationRunner init(CustomerService customerService, ItemService itemService,
                                  CartService cartService, ProductService productService) {

        return args -> {
            // Create customers
            Customer c1 = new Customer("John Cashton", 45, "john-cashton@mail.com", "6936123456",
                    Set.of(new Address("Bronx Highway", "48A", "New York", "10451", "USA")));
            Customer c2 = new Customer("Maria Buyings", 30, "maria-buyings@mail.com", "6936654321",
                    Set.of(new Address("Ashton Ct", "120", "Miami", "33145", "USA")));
            Customer c3 = new Customer("Elena Spendings", 34, "elena-spendings@mail.com", "6936123654",
                    Set.of(new Address("Lincoln Boulevard", "18B", "Los Angeles", "2633", "USA")));
            Customer c4 = new Customer("George Poorton", 28, "george-poorton@mail.com", "6936654321",
                    Set.of(new Address("Hells Kitchen", "192", "New York", "10018", "USA")));
            Customer c5 = new Customer("Harris Philton", 45, "harris-philton@mail.com", "6936652134",
                    Set.of(new Address("Beverly Hills", "69A", "Los Angeles", "90212", "USA")));

            // Save customers to DB
            customerService.addCustomers(List.of(c1, c2, c3, c4, c5));

            // Create Products
            Product p1 = new Product("Gucci Dress", 3, 800.0);
            Product p2 = new Product("Playstation 5", 20, 600.0);
            Product p3 = new Product("LG TV", 10, 750.0);
            Product p4 = new Product("Adidas Sneakers", 30, 80.0);
            Product p5 = new Product("Heineken Beer", 100, 1.5);
            Product p6 = new Product("Fender Guitar", 3, 1500.0);
            Product p7 = new Product("Xbox Series X", 30, 550.0);
            Product p8 = new Product("Samsung TV 42", 10, 800.0);
            Product p9 = new Product("iPhone 14 Pro", 15, 1400.0);
            Product p10 = new Product("Samsung S22", 15, 1350.0);

            // Save Products to DB
            productService.saveProducts(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));


            List<Cart> customerCarts = cartService.findAllCarts();
            List<Item> availableItems = itemService.getItems();

            Random random = new Random();
            customerCarts.forEach(cart -> {
                IntStream.range(0,3).forEach(value -> {

                    Item item = availableItems.get(random.nextInt(0, availableItems.size()));

                    availableItems.remove(item);
                    item.setAvailable(false);

                    Product product = productService
                            .getProductById(item.getProduct().getId())
                            .orElseThrow(EntityExistsException::new);
                    product.setQuantity(product.getQuantity() - 1);
                    productService.saveProduct(product);

                    cart.addItem(item);
                });
            });
            cartService.saveAllCarts(customerCarts);
        };
    }

}
