package vietdung.ecom2_tvdung.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vietdung.ecom2_tvdung.model.Role;
import vietdung.ecom2_tvdung.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	@Transactional
	@Query(value = "Select * From user Where email = :myemail", nativeQuery = true)
	User findByEmail(@Param("myemail")String email);

	@Transactional
	@Modifying // It means it's not a select statement
	@Query(value = "UPDATE user set password = :mypassword Where email = :myemail", nativeQuery = true)
	void setNewPassword(@Param("myemail") String email, @Param("mypassword") String password);
        
        @Transactional
	@Query(value = "Select password From user WHERE id = :myid", nativeQuery = true)
	String getOldPassword(@Param("myid") Long id);
        
        
        @Transactional
	@Query(value = "SELECT role \n" +
                        "FROM user \n" +
                        "WHERE email = :myemail", nativeQuery = true)
	Role getRoleByEmail(@Param("myemail")String email);
        
        @Transactional
	@Query(value = "SELECT role \n" +
                        "FROM user \n" +
                        "WHERE id = :myid", nativeQuery = true)
	Role getRoleByUserId(@Param("myid")Long id);
        
        
        @Transactional
	@Query(value = "SELECT user_id from customer \n" +
                        "WHERE id = :myCusId", nativeQuery = true)
	Long getUserIdByCustomerID(@Param("myCusId")Long myCusId);
}
