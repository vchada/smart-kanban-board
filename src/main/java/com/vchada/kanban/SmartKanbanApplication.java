package com.vchada.kanban;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class SmartKanbanApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartKanbanApplication.class, args);
    }
}

@RestController
@RequestMapping("/api/cards")
class KanbanController {
    private final List<Card> cards = new ArrayList<>(List.of(
            new Card(UUID.randomUUID().toString(), "Design REST API", "BACKLOG", "backend"),
            new Card(UUID.randomUUID().toString(), "Build board UI", "IN_PROGRESS", "frontend")
    ));

    @GetMapping
    List<Card> all() {
        return cards;
    }

    @PostMapping
    Card create(@Valid @RequestBody CardRequest request) {
        Card card = new Card(UUID.randomUUID().toString(), request.title(), "BACKLOG", request.label());
        cards.add(card);
        return card;
    }

    @PatchMapping("/{id}/move")
    Card move(@PathVariable String id, @Valid @RequestBody MoveRequest request) {
        Card current = cards.stream().filter(card -> card.id().equals(id)).findFirst().orElseThrow();
        Card moved = new Card(current.id(), current.title(), request.column(), current.label());
        cards.remove(current);
        cards.add(moved);
        return moved;
    }

    record Card(String id, String title, String column, String label) {
    }

    record CardRequest(@NotBlank String title, @NotBlank String label) {
    }

    record MoveRequest(@NotBlank String column) {
    }
}
