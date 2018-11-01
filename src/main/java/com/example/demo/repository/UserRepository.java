package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * description : user repository
 * @author FLEXI
 * @date
 */
public interface UserRepository extends JpaRepository<User,String> {

    /**
     * select u from users u where u.name = ?1
     * @param username
     * @return
     */
    List<User> findByUsername(String username);

    /**
     * select u from users u where id in (?,?,?)
     * @param ids
     * @return
     */
    List<User> findByIdIn(Collection<String> ids);


}
