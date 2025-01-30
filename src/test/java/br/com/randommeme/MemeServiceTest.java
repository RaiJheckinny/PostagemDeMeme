package br.com.randommeme;

import br.com.randommeme.domain.DTO.CreateMemeDTO;
import br.com.randommeme.domain.DTO.UpdateMemeDTO;
import br.com.randommeme.domain.Meme;
import br.com.randommeme.service.MemeService;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemeServiceTest {

    @Autowired
    private MemeService memeService;

    private UUID memeId;

    @BeforeEach
    void setUp() {
        CreateMemeDTO createMemeDTO = new CreateMemeDTO("Funny Meme","John Doe", "A very funny meme","https://example.com/meme.jpg");
        Meme meme = memeService.createMeme(createMemeDTO);
        memeId = meme.getId();
    }

    @Test
    void testCreateMeme() {
        CreateMemeDTO createMemeDTO = new CreateMemeDTO("New Meme", "Another meme", "Jane Doe", "https://example.com/new-meme.jpg");

        Meme createdMeme = memeService.createMeme(createMemeDTO);

        assertNotNull(createdMeme);
        assertEquals("New Meme", createdMeme.getTitle());
        assertEquals("Another meme", createdMeme.getAuthor());
        assertEquals("Jane Doe", createdMeme.getDescription());
    }

    @Test
    void testGetMemeById() {
        memeService.createMeme(new CreateMemeDTO("Funny Meme", "A very funny meme", "John Doe", "https://example.com/meme.jpg"));

        Meme foundMeme = memeService.getMemeById(memeId);

        assertNotNull(foundMeme);
        assertEquals(memeId, foundMeme.getId());
    }

    @Test
    void testGetAllMemes() {
        memeService.createMeme(new CreateMemeDTO("Funny Meme", "A very funny meme", "John Doe", "https://example.com/meme.jpg"));

        List<Meme> memes = memeService.getAllMemes();

        assertFalse(memes.isEmpty());
        assertTrue(memes.size() > 0);
    }

    @Test
    void testUpdateMeme() {
        UpdateMemeDTO updateMemeDTO = new UpdateMemeDTO("Updated Title", "Updated Desc", "Jane Doe", "https://example.com/new-meme.jpg");

        Meme updatedMeme = memeService.updateMeme(updateMemeDTO, memeId);

        assertNotNull(updatedMeme);
        assertEquals("Updated Title", updatedMeme.getTitle());
        assertEquals("Updated Desc", updatedMeme.getAuthor());
        assertEquals("Jane Doe", updatedMeme.getDescription());
    }

    @Test
    void testDeleteMeme() {
        memeService.createMeme(new CreateMemeDTO("Funny Meme", "A very funny meme", "John Doe", "https://example.com/meme.jpg"));

        Meme deletedMeme = memeService.deleteMeme(memeId);

        assertNull(deletedMeme);
    }

    @Test
    void testFindByTitleContainingIgnoreCase() {
        memeService.createMeme(new CreateMemeDTO("Funny Meme", "A very funny meme", "John Doe", "https://example.com/meme.jpg"));

        List<Meme> memes = memeService.findByTitleContainingIgnoreCase("Funny");

        assertFalse(memes.isEmpty());
        assertTrue(memes.size() > 0);
    }

    @Test
    void testGetRandomMeme() {
        memeService.createMeme(new CreateMemeDTO("Funny Meme", "A very funny meme", "John Doe", "https://example.com/meme.jpg"));

        Meme randomMeme = memeService.getRandomMeme();

        assertNotNull(randomMeme);
    }
}

