package com.petshop.trabalho.pet;

import com.petshop.trabalho.pet.request.PetRequest;
import com.petshop.trabalho.response.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pet")
@Slf4j
@RestController
@RequestMapping("/api/pet")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<Response<List<Pet>>> getValues(){
        Response<List<Pet>> response = new Response<>();
        try {
            response.setData(petService.getAll());
        } catch (Exception e){
            log.error("Erro buscar todas os pets.");
            response.getErrors().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Pet>> getValueById (@PathVariable(name = "id") Long id) {
        Response<Pet> response = new Response<>();
        try{
            response.setData(petService.findById(id));
        } catch (Exception e){
            log.error("Erro ao encontrar o Pet de id: {}", id);
            response.getErrors().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Response<Pet>> saveValues (PetDTO petDTO) {
        Response<Pet> response = new Response<>();
        try{
            log.info("Salvando Pet: {}", petDTO);
            response.setData(petService.save(petDTO));
            log.info("Pet salvo com sucesso.");
        } catch (Exception e){
            log.error("Erro salvar Pet: {}", petDTO);
            response.getErrors().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @PutMapping("/{id}")
    public ResponseEntity<Response<Pet>> updateValues(@PathVariable(name = "id")Long id, PetRequest petRequest) {
        Response<Pet> response = new Response<>();
        try{
            log.info("Atualizando Pet: {}", petRequest);
            response.setData(petService.updateCharacteristics(id, petRequest));
            log.info("Pet atualizado com sucesso.");
        } catch (Exception e){
            log.error("Erro atualizar Pet: {}", petRequest);
            response.getErrors().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Pet>> delete(@PathVariable(name = "id") Long id) {
        Response<Pet> response = new Response<>();
        try{
            log.info("Deletando Pet de id: {}", id);
            response.setData(petService.delete(id));
            log.info("Pet deletado com sucesso.");
        } catch (Exception e){
            log.error("Erro deletar Pet de id: {}", id);
            response.getErrors().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
