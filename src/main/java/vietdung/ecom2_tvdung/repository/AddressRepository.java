/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vietdung.ecom2_tvdung.model.Address;
/**
 *
 * @author TranVietDung
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    
    // Custom queries for Book if needed
    @Transactional
	@Query(value = "SELECT address_id from customer \n" +
                        "WHERE id = :myCusId", nativeQuery = true)
	Long getAddressIdByCustomerID(@Param("myCusId")Long myCusId);
}