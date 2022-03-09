package com.petshop.trabalho.consultation;

import com.petshop.trabalho.response.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Consultation")
@Slf4j
@RestController
@RequestMapping("/api/consultation")
public class ConsultationController {
    private final ConsultationService consultationService;

    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @GetMapping
    public ResponseEntity<Response<List<Consultation>>> getValues (){
        Response<List<Consultation>> response = new Response<>();
        try {
            response.setData(consultationService.getAll());
        } catch (Exception e){
            log.error("Erro buscar todas as consultas.");
            response.getErrors().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Consultation>> getValueById (@PathVariable(name = "id") Long id) {
        Response<Consultation> response = new Response<>();
        try{
            response.setData(consultationService.findById(id));
        } catch (Exception e){
            log.error("Erro ao encontrar a Consulta de id: {}", id);
            response.getErrors().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Response<Consultation>> saveValues (ConsultationDTO consultationDTO) {
        Response<Consultation> response = new Response<>();
        try{
            log.info("Salvando Consulta: {}", consultationDTO);
            response.setData(consultationService.save(consultationDTO));
            log.info("Consulta salva com sucesso.");
        } catch (Exception e){
            log.error("Erro salvar Consulta: {}", consultationDTO);
            response.getErrors().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Consultation>> delete(@PathVariable(name = "id") Long id) {
        Response<Consultation> response = new Response<>();
        try{
            log.info("Deletando Consulta de id: {}", id);
            response.setData(consultationService.delete(id));
            log.info("Consulta deletada com sucesso.");
        } catch (Exception e){
            log.error("Erro deletar Consulta de id: {}", id);
            response.getErrors().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return ResponseEntity.ok(response);
    }

}
