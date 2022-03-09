package com.petshop.trabalho.person;

import com.petshop.trabalho.response.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Person")
@Slf4j
@RestController
@RequestMapping("/api/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<Response<List<Person>>> getValues(){
        Response<List<Person>> response = new Response<>();
        try {
            response.setData(personService.getAll());
        } catch (Exception e){
            log.error("Erro buscar todas as pessoas.");
            response.getErrors().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Person>> getValueById (@PathVariable(name = "id") Long id) {
        Response<Person> response = new Response<>();
        try{
            response.setData(personService.findById(id));
        } catch (Exception e){
            log.error("Erro ao encontrar a Pessoa de id: {}", id);
            response.getErrors().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Response<Person>> saveValues (PersonDTO personDTO) {
        Response<Person> response = new Response<>();
        try{
            log.info("Salvando Pessoa: {}", personDTO);
            response.setData(personService.save(personDTO));
            log.info("Pessoa salva com sucesso.");
        } catch (Exception e){
            log.error("Erro salvar Pessoa: {}", personDTO);
            response.getErrors().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Person>> delete(@PathVariable(name = "id") Long id) {
        Response<Person> response = new Response<>();
        try{
            log.info("Deletando Pessoa de id: {}", id);
            response.setData(personService.delete(id));
            log.info("Pessoa deletada com sucesso.");
        } catch (Exception e){
            log.error("Erro deletar Pessoa de id: {}", id);
            response.getErrors().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
