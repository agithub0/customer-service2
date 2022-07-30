package customer.api.v1.repository;

import customer.api.v1.model.ZipCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipRepository extends JpaRepository<ZipCode, Integer> {
}
