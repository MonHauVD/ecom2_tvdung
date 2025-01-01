package vietdung.ecom2_tvdung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vietdung.ecom2_tvdung.model.Item;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vietdung.ecom2_tvdung.model.Catalog;
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    
//    Item findItemById(Long id);
//    
//    @Transactional
//    @Modifying
//    @Query(value = "DELETE FROM item WHERE id = :myItemId", nativeQuery = true)
//    void deleteItemById(@Param("myItemId") Long id);
    
    @Transactional
    @Query(value = "SELECT catalog from item \n" +
                    "WHERE id = :myItemId", nativeQuery = true)
    Catalog getCatalogIdByItemID(@Param("myItemId") Long itemId);
    
    @Transactional
    @Query(value = "SELECT item_id from book \n" +
                    "WHERE id = :myBookId", nativeQuery = true)
    Long getItemIdByBookID(@Param("myBookId") Long bookId);
    
    @Transactional
    @Query(value = "SELECT item_id from clothes \n" +
                    "WHERE id = :myClothesId", nativeQuery = true)
    Long getItemIdByClothesID(@Param("myClothesId") Long clothesId);
    
    @Transactional
    @Query(value = "SELECT item_id from electronic \n" +
                    "WHERE id = :myElectronicId", nativeQuery = true)
    Long getItemIdByElectronicID(@Param("myElectronicId") Long electronicId);
    
    @Transactional
    @Query(value = "SELECT item_id from laptop \n" +
                    "WHERE id = :myLaptopId", nativeQuery = true)
    Long getItemIdByLaptopID(@Param("myLaptopId") Long laptopId);
    
    @Transactional
    @Query(value = "SELECT item_id from mobile_phone \n" +
                    "WHERE id = :myMobilePhoneId", nativeQuery = true)
    Long getItemIdByMobilePhoneID(@Param("myMobilePhoneId") Long mobilePhoneId);
    
    @Transactional
    @Query(value = "SELECT item_id from shoes \n" +
                    "WHERE id = :myShoesId", nativeQuery = true)
    Long getItemIdByShoesID(@Param("myShoesId") Long shoesId);
    
//    catalog: book, clothes, electronic, laptop, mobilephone, shoes
//              0,      1,      2,          3,      4,          5
    @Transactional
        @Query(value = "SELECT COUNT(*) FROM item WHERE name = :myBookName and catalog = 0", nativeQuery = true)
        int countBookByBookName(@Param("myBookName") String myBookName);
        
    @Transactional
    @Query(value = "SELECT it.* FROM item it\n" +
                    "WHERE it.catalog = 0", nativeQuery = true)
    List<Item> getAllItemWithBook();
    
    @Transactional
    @Query(value = "SELECT it.* FROM item it\n" +
                    "WHERE it.catalog = 1", nativeQuery = true)
    List<Item> getAllItemWithClothes();
    
    @Transactional
    @Query(value = "SELECT it.* FROM item it\n" +
                    "WHERE it.catalog = 2", nativeQuery = true)
    List<Item> getAllItemWithElectronic();
    
    @Transactional
    @Query(value = "SELECT it.* FROM item it\n" +
                    "WHERE it.catalog = 3", nativeQuery = true)
    List<Item> getAllItemWithLaptop();
    
    @Transactional
    @Query(value = "SELECT it.* FROM item it\n" +
                    "WHERE it.catalog = 4", nativeQuery = true)
    List<Item> getAllItemWithMobilephone();
    
    @Transactional
    @Query(value = "SELECT it.* FROM item it\n" +
                    "WHERE it.catalog = 5", nativeQuery = true)
    List<Item> getAllItemWithShoes();
}
