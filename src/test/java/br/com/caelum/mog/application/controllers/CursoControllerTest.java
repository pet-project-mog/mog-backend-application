package br.com.caelum.mog.application.controllers;


import br.com.caelum.mog.domain.models.Curso;
import br.com.caelum.mog.domain.repositories.CursoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Duration;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CursoControllerTest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private CursoRepository repository;



    @Before
    public void setup(){
        repository.save(new Curso("Java e Orientação a objetos", new BigDecimal("2290"), Duration.ofHours(40)));
        repository.save(new Curso("Java para Desenvolvimento web", new BigDecimal("2890"), Duration.ofHours(40)));
    }



    @Test
    public void deveSerPossivelRetornarTodosOsCursos() throws Exception{



        mvc.perform(get("/cursos")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(equalTo(1))))
                .andExpect(jsonPath("$[0].nome", is("Java e Orientação a objetos")))
                .andExpect(jsonPath("$[0].valor", is(closeTo(2290, 0.0))))
                .andExpect(jsonPath("$[1].cargaHoraria", is("PT40H")))
                .andExpect(jsonPath("$[1].nome", is("Java para Desenvolvimento web")))
                .andExpect(jsonPath("$[1].valor", is(closeTo(2890, 0.0))))
                .andExpect(jsonPath("$[1].cargaHoraria", is("PT40H")));

    }


    @Test
    public void deveSerPossivelRetornarUmCursoAtravesDoId() throws Exception {


        mvc.perform(get("/cursos/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(equalTo(1))))
                .andExpect(jsonPath("$.nome", is("Java e Orientação a objetos")))
                .andExpect(jsonPath("$.valor", is(closeTo(2290, 0.0))))
                .andExpect(jsonPath("$.cargaHoraria", is("PT40H")));

    }


    @Test
    public void deveSerRetornadoNotFoundQuandoEnviadoUmIdInvalido() throws Exception {

        mvc.perform(    get("/cursos/{id}", 123)
                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }


}
