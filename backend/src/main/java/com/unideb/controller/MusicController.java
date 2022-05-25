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

    //
    @CrossOrigin
    @PostMapping("/songs")
    @ResponseStatus(HttpStatus.CREATED)
    Music newSong(@RequestBody Music newSong) {
        return repository.save(newSong);
    }

    // Read (one)
    @CrossOrigin
    @GetMapping("/songs/{id}")
    Music findOne(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new SongNotFoundException(id));
    }

    // Read (all)
    @CrossOrigin
    @GetMapping("/songs")
    List<Music> findAll() {
        return repository.findAll();
    }

    // Update
    @CrossOrigin
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
    @CrossOrigin
    @DeleteMapping("/songs/{id}")
    void deleteBook(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

    // Delete (all)
    @CrossOrigin
    @DeleteMapping("/deletesongs")
    void deleteAllSongs() {
        List<Music> songs = repository.findAll();

        for(Music entry : songs)
        {
            repository.deleteById(entry.getId());
        }
    }
}
