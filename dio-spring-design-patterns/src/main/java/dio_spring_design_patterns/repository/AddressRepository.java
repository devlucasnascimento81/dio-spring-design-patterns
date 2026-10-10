package dio_spring_design_patterns.repository;

import dio_spring_design_patterns.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório responsável pelas operações de persistência da entidade Address.
 * Estende CrudRepository para operações básicas de CRUD.
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, String> {
}
