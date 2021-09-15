package bowling;

import bowling.domain.NormalFrame;
import bowling.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PlayerTest {
    Player player;
    NormalFrame currentFrame;

    @BeforeEach
    void init() {
        String name = "RSH";
        player = new Player(name);
        NormalFrame startFrame = (NormalFrame) player.startFrame();
        currentFrame = (NormalFrame) startFrame.nextFrame(5);
        player.addFrame(currentFrame);
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
        assertThat(player.getFrame(1)).isEqualTo(new NormalFrame(5, 1));
    }

    @Test
    @DisplayName("프레임 출력")
    void scores() {
        currentFrame.secondBall(5);
        List<String> scores = currentFrame.scores();
        assertAll(
                () -> assertThat(scores.get(0)).isEqualTo("5"),
                () -> assertThat(scores.get(1)).isEqualTo("5"));
    }
}
