package bowling;

import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.Player;
import bowling.domain.PlayerName;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Player player;

    @BeforeEach
    void init() {
        String name = "RSH";
        player = new Player(name);
        player.createEmptyFrame();
    }

    @Test
    @DisplayName("유저 생성")
    void createUser() {
        String name = "RSH";
        assertThat(player.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("현재 프레임")
    void playerFrame() {
        Frame currentFrame = player.firstBall(5);
        assertThat(currentFrame).isEqualTo(new Frame(5));
    }

    @Test
    @DisplayName("프레임 출력")
    void scores() {
        Frame currentFrame = player.firstBall(5);
        currentFrame.secondBall(5);
        List<String> scores = currentFrame.scores();
        assertAll(
                () -> assertThat(scores.get(0)).isEqualTo("5"),
                () -> assertThat(scores.get(1)).isEqualTo("5"));
    }
    
    @Test
    @DisplayName("마지막 프레임")
    void finalFrame() {
        Frame finalFrame = player.finalFrame(5);
        assertThat(finalFrame).isEqualTo(new FinalFrame(5));
    }
}
