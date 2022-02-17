package application.business.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import application.business.entities.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {

}
