package com.unipi.ipap.springnativecrudgraalvm;

import com.unipi.ipap.springnativecrudgraalvm.entity.Address;
import com.unipi.ipap.springnativecrudgraalvm.entity.Cart;
import com.unipi.ipap.springnativecrudgraalvm.entity.Customer;
import com.unipi.ipap.springnativecrudgraalvm.entity.Item;
import com.unipi.ipap.springnativecrudgraalvm.repository.CartRepository;
import com.unipi.ipap.springnativecrudgraalvm.repository.CustomerRepository;
import com.unipi.ipap.springnativecrudgraalvm.repository.ItemRepository;
import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.nativex.hint.TypeHint;

import java.util.*;
import java.util.stream.IntStream;

@SpringBootApplication
@TypeHint(types = MySQL5Dialect.class, typeNames = "org.hibernate.dialect.MySQL5Dialect")
public class SpringNativeCrudGraalvmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringNativeCrudGraalvmApplication.class, args);
    }

    @Bean
    public ApplicationRunner init(CustomerRepository customerRepository, ItemRepository itemRepository, CartRepository cartRepository) {

        return args -> {
            // Create customers
            Customer c1 = new Customer("John Cashton", 45, "john-cashton@mail.com", "6936123456",
                    Set.of(new Address("Bronx Highway", "48A", "New York")), new Cart());
            Customer c2 = new Customer("Maria Buyings", 30, "maria-buyings@mail.com", "6936654321",
                    Set.of(new Address("Ashton Ct", "120", "Miami")), new Cart());
            Customer c3 = new Customer("Elena Spendings", 34, "elena-spendings@mail.com", "6936123654",
                    Set.of(new Address("Lincoln Boulevard", "18B", "Los Angeles")), new Cart());
            Customer c4 = new Customer("George Poorton", 28, "george-poorton@mail.com", "6936654321",
                    Set.of(new Address("Hells Kitchen", "192", "New York")), new Cart());
            Customer c5 = new Customer("Harris Philton", 45, "harris-philton@mail.com", "6936652134",
                    Set.of(new Address("Beverly Hills", "69A", "Los Angeles")), new Cart());

            // Save customers to DB
            List<Customer> savedCustomers = customerRepository.saveAll(List.of(c1, c2, c3, c4, c5));

            // Create items
            Item i1 = new Item("Dress", 200.0);
            Item i2 = new Item("Playstation 5", 600.0);
            Item i3 = new Item("LG TV", 1000.0);
            Item i4 = new Item("Shoes", 150.0);
            Item i5 = new Item("Beer", 5.0);
            Item i6 = new Item("Guitar", 1000.0);
            Item i7 = new Item("Xbox series X", 550.0);
            Item i8 = new Item("Samsung TV", 1000.0);
            Item i9 = new Item("iPhone 14 Pro", 1400.0);
            Item i10 = new Item("Samsung S22", 1350.0);

            // Save items to DB
            List<Item> savedItems = itemRepository.saveAll(List.of(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10));

            List<Cart> customerCarts = cartRepository.findAll();

            Random random = new Random();

            customerCarts.forEach(cart -> {
                IntStream.range(0,2).forEach(value -> {
                    Item item = savedItems.get(random.nextInt(0, savedItems.size()));
                    cart.addItem(item);
                    savedItems.remove(item);
                });
            });
            cartRepository.saveAll(customerCarts);
        };
    }

}
