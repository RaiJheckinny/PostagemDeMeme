package br.com.randommeme.controller;

import br.com.randommeme.domain.DTO.CreateMemeDTO;
import br.com.randommeme.domain.DTO.UpdateMemeDTO;
import br.com.randommeme.domain.Meme;
import br.com.randommeme.service.MemeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/meme")
public class MemeController {
    @Autowired
    MemeService memeService;

    @PostMapping("/create")
    @Operation(summary = "Cria um meme", description = "Cria um meme passando as informações Title, Autor, Descrição e Url do Meme. Retorna o meme criado.")
    public ResponseEntity<Meme> createMeme(@RequestBody CreateMemeDTO createMemeDTO) {
        Meme meme = memeService.createMeme(createMemeDTO);
        return new ResponseEntity<>(meme, HttpStatus.OK);
    }

    @GetMapping("/search/{title}")
    @Operation(summary = "Busca memes por título", description = "Retorna uma lista de memes cujo título contenha a string fornecida, ignorando maiúsculas e minúsculas.")
    public ResponseEntity<List<Meme>> getByTitle(@PathVariable String title) {
        List<Meme> memes = memeService.findByTitleContainingIgnoreCase(title);
        return new ResponseEntity<>(memes, HttpStatus.OK);
    }
    @GetMapping("/getall")
    @Operation(summary = "Obtém todos os memes", description = "Retorna uma lista com todos os memes cadastrados.")
    public ResponseEntity<List<Meme>> getAllMemes() {
        List<Meme> memes = memeService.getAllMemes();
        return new ResponseEntity<>(memes, HttpStatus.OK);
    }

    @GetMapping("/randommeme")
    @Operation(summary = "Obtém um meme aleatório", description = "Retorna um meme aleatório do banco de dados. Se não houver memes, retorna 404.")
    public ResponseEntity<Meme> getRandomMeme() {
        Meme meme = memeService.getRandomMeme();
        if (meme == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(meme, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deleta um meme", description = "Deleta um meme com o ID fornecido e retorna o meme deletado.")
    public ResponseEntity<Meme> deleteMeme(@PathVariable UUID id) {
        Meme meme = memeService.deleteMeme(id);
        return new ResponseEntity<>(meme, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    @Operation(summary = "Atualiza um meme", description = "Atualiza um meme com o ID fornecido e as novas informações passadas no corpo da requisição.")
    public ResponseEntity<Meme> updateMeme(@RequestBody UpdateMemeDTO updateMemeDTO, @PathVariable UUID id) {
        Meme meme = memeService.updateMeme(updateMemeDTO, id);
        return new ResponseEntity<>(meme, HttpStatus.OK);
    }
}
