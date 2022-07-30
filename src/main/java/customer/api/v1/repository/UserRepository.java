package customer.api.v1.repository;

import customer.api.v1.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users,String> {
    @Query("SELECT COUNT(*) FROM Users")
    public int numberOfEntities();
}
