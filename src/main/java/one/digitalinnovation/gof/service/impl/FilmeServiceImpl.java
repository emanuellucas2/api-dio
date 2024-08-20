package one.digitalinnovation.gof.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.domain.model.Filme;
import one.digitalinnovation.gof.service.FilmeService;
import one.digitalinnovation.gof.domain.repository.FilmeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeServiceImpl implements FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Override
    public Filme salvarFilme(Filme filme) {
        return filmeRepository.save(filme);
    }

    @Override
    public List<Filme> listarTodosFilmes() {
        return filmeRepository.findAll();
    }

    @Override
    public Optional<Filme> obterFilmePorId(Long id) {
        return filmeRepository.findById(id);
    }

    @Override
    public Filme atualizarFilme(Long id, Filme filme) {
        Optional<Filme> filmeExistente = filmeRepository.findById(id);
        if (filmeExistente.isPresent()) {
            Filme atualizacao = filmeExistente.get();
            atualizacao.setTitulo(filme.getTitulo());
            atualizacao.setDiretor(filme.getDiretor());
            atualizacao.setAnoLancamento(filme.getAnoLancamento());
            return filmeRepository.save(atualizacao);
        } else {
            throw new RuntimeException("Filme não encontrado com id: " + id);
        }
    }

    @Override
    public void deletarFilme(Long id) {
        filmeRepository.deleteById(id);
    }
}
