package io.github.jjelliott.roadtrip.games.alphabet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlphabetRepository extends JpaRepository<Alphabet, String> {
    Alphabet findByRoomId(String roomId);

}
