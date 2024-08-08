package io.github.jjelliott.roadtrip.games.alphabet;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Alphabet {
    @Id
    private String roomId;
    private int idx;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }
}
