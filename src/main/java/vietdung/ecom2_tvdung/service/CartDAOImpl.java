package vietdung.ecom2_tvdung.service;

import vietdung.ecom2_tvdung.repository.CartItemRepository;
import vietdung.ecom2_tvdung.repository.BookRepository;
import vietdung.ecom2_tvdung.repository.CustomerRepository;
import vietdung.ecom2_tvdung.repository.ItemRepository;
import vietdung.ecom2_tvdung.repository.CartRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vietdung.ecom2_tvdung.model.*;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CartDAOImpl {
     @Autowired
    private CartRepository cartDAO;

    @Autowired
    private CustomerRepository customerDAO;

    @Autowired
    private CartItemRepository cartItemDAO;

    @Autowired
    private BookRepository bookDAO;

    @Autowired
    private ItemRepository itemDAO;

    @Transactional
    public Cart addBookToCustomerCart(Long customerId, Long bookId, int quantity) {
        Optional<Customer> customerOpt = customerDAO.findById(customerId);

        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();

            // Retrieve or create a cart for this customer
            Cart cart = cartDAO.findByCustomerId(customerId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setCustomer(customer);  // Associate the cart with customerId
                    return cartDAO.save(newCart);
                });

            // Retrieve the book and associated item
            Optional<Book> bookOpt = bookDAO.findById(bookId);
            if (bookOpt.isPresent()) {
                Book book = bookOpt.get();
                Optional<Item> itemOpt = itemDAO.findById(Long.valueOf(book.getIdItem()));

                if (itemOpt.isPresent()) {
                    Item item = itemOpt.get();

                    // Create and associate the CartItem with the Cart and Item
                    CartItem cartItem = new CartItem();
                    cartItem.setCart(cart);
                    cartItem.setItem(item);
                    cartItem.setQuantity(quantity);

                    // Add CartItem to Cart's items list
                    cart.getCartItems().add(cartItem);

                    // Update the cart total cost
                    cart.calculateTotalCost();

                    // Save the CartItem and Cart
                    cartItemDAO.save(cartItem);
                    return cartDAO.save(cart);
                } else {
                    throw new RuntimeException("Associated Item not found for Book");
                }
            } else {
                throw new RuntimeException("Book not found");
            }
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    public Optional<Cart> getCartByCustomerId(Long customerId) {
        return cartDAO.findByCustomerId(customerId);
    }
}
