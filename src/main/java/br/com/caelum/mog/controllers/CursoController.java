package br.com.caelum.mog.controllers;

import br.com.caelum.mog.domain.models.Curso;
import br.com.caelum.mog.domain.repositories.CursoRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cursos")
public class CursoController {


    private final CursoRepository cursoRepository;

    public CursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Curso> getAll(){
        return cursoRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Curso> getOne(@PathVariable Long id){
        return cursoRepository
                .findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
}
