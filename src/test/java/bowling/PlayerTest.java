package bowling;

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
    }

    @Test
    @DisplayName("유저 생성")
    void createUser() {
        String name = "RSH";
        assertThat(player.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("프레임 출력")
    void scores() {
        player.firstBall(5);
        player.secondBall(5);
        Frame currentFrame = player.getFrame(0);
        List<String> scores = currentFrame.scores();
        assertAll(
                () -> assertThat(scores.get(0)).isEqualTo("5"),
                () -> assertThat(scores.get(1)).isEqualTo("5"));
    }
}
