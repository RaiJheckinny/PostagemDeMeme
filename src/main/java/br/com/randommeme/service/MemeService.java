package br.com.randommeme.service;

import br.com.randommeme.Exeception.MemeNotFoundException;
import br.com.randommeme.domain.DTO.CreateMemeDTO;
import br.com.randommeme.domain.DTO.UpdateMemeDTO;
import br.com.randommeme.domain.Meme;
import br.com.randommeme.repository.IMemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class MemeService {
    @Autowired
    private IMemeRepository memeRepository;
    public Meme createMeme(CreateMemeDTO createMemeDTO) {
        Meme meme = new Meme(createMemeDTO);
        return memeRepository.save(meme);
    }

    public Meme getMemeById(UUID id) {
        return memeRepository.findById(id).orElse(null);
    }
    public List<Meme> getAllMemes() {
        List<Meme> memes = memeRepository.findAll();
        return memes;
    }
    public Meme updateMeme(UpdateMemeDTO updateMemeDTO, UUID id) {
        if (!memeRepository.existsById(id)) {
            new MemeNotFoundException(id);
        }
        Meme memeBD = memeRepository.findById(id).orElse(null);

        memeBD.setTitle(updateMemeDTO.title());
        memeBD.setDescription(updateMemeDTO.description());
        memeBD.setAuthor(updateMemeDTO.author());
        memeBD.setUrlImage(updateMemeDTO.urlImage());

        memeRepository.save(memeBD);
        return memeRepository.save(memeBD);
    }
    public Meme deleteMeme(UUID id) {
        memeRepository.deleteById(id);
        return getMemeById(id);
    }

    public List<Meme> findByTitleContainingIgnoreCase(String title) {
        String[] partsTitle = title.split("-");
        List<Meme> result = new ArrayList<Meme>();
        for (String part : partsTitle) {
            List<Meme> memes = memeRepository.findByTitleContainingIgnoreCase(part);
            for (Meme meme : memes) {
                result.add(meme);
            }
        }

        return result;
    }

    public Meme getRandomMeme() {
        List<Meme> memes = memeRepository.findAll();
        Random random = new Random();
        return memes.get(random.nextInt(memes.size()));
    }
}
