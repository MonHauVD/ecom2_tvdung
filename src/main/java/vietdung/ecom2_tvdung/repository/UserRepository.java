package vietdung.ecom2_tvdung.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
	@Query(value = "SELECT role \n" +
                        "FROM users \n" +
                        "WHERE email = :myemail", nativeQuery = true)
	String getRoleByEmail(@Param("myemail")String email);
}
