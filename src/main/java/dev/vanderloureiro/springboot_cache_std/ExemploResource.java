package dev.vanderloureiro.springboot_cache_std;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ExemploResource {

    Map<Long, Filme> filmes = Map.of(
            1L, new Filme("1", "1"),
            2L, new Filme("2", "2"),
            3L, new Filme("3", "3"),
            4L, new Filme("4", "4")
    );

    @Cacheable("filmes")
    @GetMapping("/{id}")
    public ResponseEntity<Filme> get(@PathVariable Long id) {
        return ResponseEntity.ok(buscaPorId(id));
    }
    
    private Filme buscaPorId(Long id) {
        try {
            Thread.sleep(1000);
            return filmes.get(id);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
