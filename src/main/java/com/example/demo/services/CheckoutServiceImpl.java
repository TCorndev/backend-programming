package com.example.demo.services;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        String error = "Cart cannot be empty.";
        //Gets cart
        Cart cart = purchase.getCart();

        //Check if cart exists
        if(cart == null){
            return new PurchaseResponse(error);
        }

        //Set cart status to ordered
        StatusType status = StatusType.ordered;
        cart.setStatus(status);

        //Generate new tracking number, add it to cart
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        //Get cart items
        Set<CartItem> cartItems = purchase.getCartItems();

        //Check if cartItems exists and is not empty
        if(cartItems == null || cartItems.isEmpty()){
            return new PurchaseResponse(error);
        }

        //add cartItems to cart
        cartItems.forEach(item -> cart.add(item));

        //Get customer, add cart to customer
        Customer customer = purchase.getCustomer();
        customer.add(cart);

        //Update customer in the database
        customerRepository.save(customer);

        //Return response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber(){

        return UUID.randomUUID().toString();
    }
}
