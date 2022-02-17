package application.business.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.business.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

}
