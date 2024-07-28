package dev.vanderloureiro.springboot_cache_std;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExemploResource {

    Logger logger = LoggerFactory.getLogger(ExemploResource.class);

    List<Filme> filmes = List.of(
            new Filme(1L, "1", "1"),
            new Filme(2L, "2", "2"),
            new Filme(3L, "3", "3"),
            new Filme(4L, "4", "4"));

    @Cacheable("filmes")
    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(buscarPorId(id));
    }

    @CacheEvict(value = "filmes")
    @GetMapping("/limpar-unico/{id}")
    public void limparCache(@PathVariable Long id) {}

    @CacheEvict(value = "filmes", allEntries = true)
    @GetMapping("/limpar-todos")
    public void limparTodos() {

    }
    
    private Filme buscarPorId(Long id) {
        try {
            logger.info("Buscando filme de ID: " + id);
            Thread.sleep(1000);
            return filmes.stream().filter(filme -> filme.id().equals(id)).findFirst().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
