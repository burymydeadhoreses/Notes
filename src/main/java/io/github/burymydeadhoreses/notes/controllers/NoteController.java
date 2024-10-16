package io.github.burymydeadhoreses.notes.controllers;

import io.github.burymydeadhoreses.notes.entities.Note;
import io.github.burymydeadhoreses.notes.services.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    public Note add(@RequestBody Note note) {
        return noteService.add(note);
    }

    @GetMapping
    public List<Note> list() {
        return noteService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> get(@PathVariable UUID id) {
        return noteService.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> update(@PathVariable UUID id, @RequestBody Note note) {
        note.setId(id);
        return noteService.update(note)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Note> delete(@PathVariable UUID id) {
        return noteService.delete(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
