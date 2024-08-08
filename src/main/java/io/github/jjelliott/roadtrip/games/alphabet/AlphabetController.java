package io.github.jjelliott.roadtrip.games.alphabet;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/{room}/alphabet")
@CrossOrigin
public class AlphabetController {

    private final AlphabetRepository repository;

    public AlphabetController(AlphabetRepository repository) {
        this.repository = repository;
    }

    @GetMapping(produces = "text/plain")
    String get(@PathVariable String room) {
        var record = repository.findByRoomId(room);
        return (record == null ? 0 : record.getIdx()) + "";
    }

    @PostMapping
    String update(@PathVariable String room) {
        var existing = repository.findByRoomId(room);
        var a = existing == null ? new Alphabet() : existing;
        a.setRoomId(room);
        a.setIdx(a.getIdx() + 1);
        repository.save(a);
        return a.getIdx() + "";
    }

    @PostMapping("/reset")
    String reset(@PathVariable String room) {
        var existing = repository.findByRoomId(room);
        var a = existing == null ? new Alphabet() : existing;
        a.setRoomId(room);
        a.setIdx(0);
        repository.save(a);
        return a.getIdx() + "";
    }

    @GetMapping("/stream-updates")
    SseEmitter gameUpdates(@PathVariable String room){
        SseEmitter emitter = new SseEmitter(-1L);
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                int i = 0;
                do {
                    var gameState = repository.findByRoomId(room);
                    if (gameState != null) {
                        SseEmitter.SseEventBuilder event = SseEmitter.event()
                                .data(gameState.getIdx())
                                .id(String.valueOf(i))
                                .name("currentLetter");
                        emitter.send(event);
                        Thread.sleep(5000);
                    }
                    i++;
                } while (true);
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }

}
