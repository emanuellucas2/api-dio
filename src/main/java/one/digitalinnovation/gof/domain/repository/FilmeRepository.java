package one.digitalinnovation.gof.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import one.digitalinnovation.gof.domain.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
