package io.github.jjelliott.roadtrip;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
@CrossOrigin
public class GameRoomController {

    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private final GameRoomRepository repository;

    public GameRoomController(GameRoomRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<GameRoom> list(){
        return repository.findAll();
    }
    @GetMapping("/{code}")
    GameRoom get(@PathVariable String code) {
        return repository.findByCode(code.toLowerCase());
    }

    @PostMapping
    GameRoom create() {
        var room = new GameRoom();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            code.append(LETTERS.charAt((int) Math.floor(Math.random() * 26)));
        }
        room.setCode(code.toString());
        return repository.save(room);
    }

}
