package br.com.randommeme.repository;

import br.com.randommeme.domain.Meme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IMemeRepository extends JpaRepository<Meme, UUID> {
    List<Meme> findByTitleContainingIgnoreCase(String title);
    Optional<Meme> findById(UUID id);
}
