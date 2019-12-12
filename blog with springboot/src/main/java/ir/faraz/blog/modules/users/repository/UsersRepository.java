package ir.faraz.blog.modules.users.repository;

import ir.faraz.blog.modules.users.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// Spring Data :)))
// no need to @repository :)))) because of extension from JPA Repository
@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

    // select * from users where email=...
    // By Spring DATA
    //Users findByEmail(String email);

    //By Native Query
    //@Query(nativeQuery = true, value = "select * from users")
    //Users userEmail();

    // By JPQL
    // select * from users where email="..."
    @Query("select u from Users u where u.email=:email")
    Users findByQuery(@Param("email") String email);

    Users findByEmail(String email);

}
