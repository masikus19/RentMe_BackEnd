package application.security.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.security.entities.Account;

public interface AccountRepository extends MongoRepository<Account, String>{

}
