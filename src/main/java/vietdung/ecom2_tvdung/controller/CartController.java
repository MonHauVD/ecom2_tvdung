/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.controller;

/**
 *
 * @author TranVietDung
 */
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vietdung.ecom2_tvdung.model.Cart;
import vietdung.ecom2_tvdung.service.CartDAOImpl;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartDAOImpl cartService;

    // Endpoint to add a book to the customer's cart
    @PostMapping("/addBook")
    public ResponseEntity<String> addBookToCart(
            @RequestParam Long customerId,
            @RequestParam Long bookId,
            @RequestParam int quantity) {
        
        try {
            Cart updatedCart = cartService.addBookToCustomerCart(customerId, bookId, quantity);
            return ResponseEntity.ok("Book added to cart successfully! Current total cost: " + updatedCart.getTotalPrice());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // Endpoint to retrieve the customer's cart by customerId
    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomerCart(@PathVariable Long customerId) {
        try {
            Optional<Cart> cart = cartService.getCartByCustomerId(customerId);
            if (cart.isPresent()) {
                return ResponseEntity.ok(cart.get());
            } else {
                return ResponseEntity.badRequest().body("No cart found for this customer.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
