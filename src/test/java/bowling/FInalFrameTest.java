package bowling;

import bowling.domain.FinalFrame;
import bowling.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FInalFrameTest {
    Player player;

    @BeforeEach
    void init() {
        String name = "RSH";
        player = new Player(name);
        player.createEmptyFrame();

        player.createEmptyFrame();
    }

    @Test
    @DisplayName("마지막 프레임 마지막 공")
    void finalBall() {
        FinalFrame finalFrame = player.finalFrame(2);
        finalFrame.secondBall(8);
        finalFrame.finalBall(10);

        assertThat(finalFrame.scores().contains("2") &&
                finalFrame.scores().contains("8") &&
                finalFrame.scores().contains("10")).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임 마지막 공 에러")
    void finalBallValidate() {
        FinalFrame finalFrame = player.finalFrame(2);
        finalFrame.secondBall(3);

        assertThatThrownBy(() ->
                finalFrame.finalBall(10))
                .isInstanceOf(RuntimeException.class);
    }


}
