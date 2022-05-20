package com.unideb.controller;

import com.unideb.error.SongNotFoundException;
import com.unideb.model.Music;
import com.unideb.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MusicController {
    @Autowired
    private MusicRepository repository;

    @GetMapping("/greet")
    String greet() {
        return "Hello World!";
    }

    // Create
    @PostMapping("/songs")
    @ResponseStatus(HttpStatus.CREATED)
    Music newSong(@RequestBody Music newSong) {
        return repository.save(newSong);
    }

    // Read (one)
    @GetMapping("/songs/{id}")
    Music findOne(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new SongNotFoundException(id));
    }

    // Read (all)
    @GetMapping("/songs")
    List<Music> findAll() {
        return repository.findAll();
    }

    // Update
    // TODO

    @PutMapping("/songs/{id}")
    Music saveOrUpdate(@RequestBody Music newSong, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setTitle(newSong.getTitle());
                    x.setAuthor(newSong.getAuthor());
                    x.setAlbum(newSong.getAlbum());
                    x.setGenre(newSong.getGenre());
                    x.setLength(newSong.getLength());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newSong.setId(id);
                    return repository.save(newSong);
                });
    }

    // Delete (one)
    @DeleteMapping("/songs/{id}")
    void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // Delete (all)
    @DeleteMapping("/deletesongs")
    void deleteAllSongs() {
        List<Music> songs = repository.findAll();

        for(Music entry : songs)
        {
            repository.deleteById(entry.getId());
        }
    }


    /*
    @GetMapping("/cheaperbooks")
    List<Book> getCheaperThan(@RequestParam BigDecimal maxprice) {
        return repository.findAll().stream().filter(x -> x.getPrice().compareTo(maxprice) < 0).collect(Collectors.toList());
    }

    // update author only
    @PatchMapping("/books/{id}")
    Book patch(@RequestBody Map<String, String> update, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {

                    String author = update.get("author");
                    if (!StringUtils.isEmpty(author)) {
                        x.setAuthor(author);

                        // better create a custom method to update a value = :newValue where id = :id
                        return repository.save(x);
                    } else {
                        throw new BookUnSupportedFieldPatchException(update.keySet());
                    }

                })
                .orElseGet(() -> {
                    throw new BookNotFoundException(id);
                });
    }
     */
}
