package vietdung.ecom2_tvdung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vietdung.ecom2_tvdung.model.Item;

import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
//    Item findByName(String name);
    
    Item findItemById(Long id);
//    List<Item> findAllByOrderByNameAsc();
//    void deleteById(Long id);
//    void createItem(Item item);
//    void addItem(Item item);
//    void updateItem(Item item);
}
