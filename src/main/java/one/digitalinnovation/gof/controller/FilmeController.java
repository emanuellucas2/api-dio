package one.digitalinnovation.gof.controller;

import one.digitalinnovation.gof.domain.model.Filme;
import one.digitalinnovation.gof.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Filmes")
public class FilmeController {

    private final FilmeService filmeService;

    @Autowired
    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping
    public List<Filme> getAllFilmes() {
        return filmeService.listarTodosFilmes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> getFilmeById(@PathVariable Long id) {
        Optional<Filme> Filme = filmeService.obterFilmePorId(id);
        return Filme.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Filme createFilme(@RequestBody Filme Filme) {
        return filmeService.salvarFilme(Filme);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> updateFilme(@PathVariable Long id, @RequestBody Filme filmeDetails) {
        Optional<Filme> Filme = filmeService.obterFilmePorId(id);
        if (Filme.isPresent()) {
            Filme filmeToUpdate = Filme.get();
            filmeToUpdate.setTitulo(filmeDetails.getTitulo());
            filmeToUpdate.setDiretor(filmeDetails.getDiretor());
            filmeToUpdate.setAnoLancamento(filmeDetails.getAnoLancamento());
            Filme updatedFilme = filmeService.salvarFilme(filmeToUpdate);
            return ResponseEntity.ok(updatedFilme);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilme(@PathVariable Long id) {
        if (filmeService.obterFilmePorId(id).isPresent()) {
            filmeService.deletarFilme(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
