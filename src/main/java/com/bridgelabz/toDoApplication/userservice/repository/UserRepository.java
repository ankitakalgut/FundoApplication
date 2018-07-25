package com.bridgelabz.toDoApplication.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.bridgelabz.toDoApplication.userservice.model.User;

/*********************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 *PURPOSE:interface that contains mongodb operations
 ***********************************************************************************/
@Repository
public interface UserRepository extends MongoRepository<User, String>
{
	public User findByEmail(String email);	
}
