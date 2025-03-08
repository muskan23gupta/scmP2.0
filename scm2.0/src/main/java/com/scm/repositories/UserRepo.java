//repositiories are used to interact with the databae and we this repositiories contains all the method which are required to connect it with the database and these methods are coming from jpa reposiiories
package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.User;
@Repository
public interface UserRepo  extends JpaRepository<User,String> {
 //extra method db related operations 
 //custum finder methods

 //custom query methods 
 //its implimentation is automatically done by spring data jpa
  Optional<User> findByEmail(String email);
 //userrepository --> service --> controller
} 
