package br.com.randommeme.domain;

import br.com.randommeme.domain.DTO.CreateMemeDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
public class Meme {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    private String description;
    @Column(name = "url_image", nullable = false)
    private String urlImage;

    public Meme(CreateMemeDTO createMemeDTO) {
        this.title = createMemeDTO.title();
        this.author = createMemeDTO.author();
        this.description = createMemeDTO.description();
        this.urlImage = createMemeDTO.urlImage();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Meme() {
    }
}
