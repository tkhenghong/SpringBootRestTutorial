package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JPA Step 7: Create a repository for User (Replaces UserDaoService.java)
// extends JpaRepository<User, Integer> means mapping User object as input with an integer as our ID (we already created it)
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
