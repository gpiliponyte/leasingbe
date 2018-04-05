package itacademy.vehicleleasingbe.leasingbe.login.test.config.dao;

import itacademy.vehicleleasingbe.leasingbe.login.test.config.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, String> {
    User findByUsername(String username);
}
