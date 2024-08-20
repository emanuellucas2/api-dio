package one.digitalinnovation.gof.service;

import java.util.List;
import java.util.Optional;

import one.digitalinnovation.gof.domain.model.Filme;

public interface FilmeService {
	
    Filme salvarFilme(Filme filme);
    List<Filme> listarTodosFilmes();
    Optional<Filme> obterFilmePorId(Long id);
    Filme atualizarFilme(Long id, Filme filme);
    void deletarFilme(Long id);
    
}
