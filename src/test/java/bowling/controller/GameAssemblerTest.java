package bowling.controller;

import bowling.controller.dto.FrameStatus;
import bowling.controller.dto.GameStatus;
import bowling.domain.Game;
import bowling.domain.pitch.Pitch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameAssemblerTest {
    private String name;

    @BeforeEach
    void setUp() {
        name = "BRK";
    }

//    @DisplayName("원하는 프레임의 핀 갯수 정보를 가져온다")
//    @Test
//    void getFramePinCount() {
//        Game game = new Game(name);
//        int first = 1;
//        int second = 2;
//        game.addPin(first);
//        game.addPin(second);
//
//        GameStatus gameStatus = GameAssembler.writeDto(game);
//
//        List<Pitch> pitchList = gameStatus.getFrameStatus(0).getPitches();
//        assertThat(pitchList.get(0).getPinCount()).isEqualTo(1);
//        assertThat(pitchList.get(1).getPinCount()).isEqualTo(2);
//    }
//
//    @DisplayName("현재 게임의 상태를 가져온다")
//    @Test
//    void getGameStatus() {
//        Game game = new Game(name);
//        game.addPin(8);
//        game.addPin(2);
//        game.addPin(1);
//
//        GameStatus gameStatus = GameAssembler.writeDto(game);
//
//        assertThat(gameStatus.getFrameStatus(0).getScore().get()).isEqualTo(11);
//        assertThat(gameStatus.getFrameStatus(3)).isEqualTo(FrameStatus.EMPTY);
//    }
}
