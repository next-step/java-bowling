package bowling;

import bowling.domain.Frame;
import bowling.domain.Player;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PlayerTest {
    @Test
    @DisplayName("플레이어테스트")
    public void playerTest() {
        Player player = new Player("KPJ");
        assertThat(player.toString()).isEqualTo("KPJ");
    }

    @Test
    @DisplayName("자릿수테스트")
    public void checkLettersTest() {
        String name = "1234";
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("3글자까지만 입력가능합니다.");
    }

    @Test
    @DisplayName("프레임테스트")
    public void frameTest() {
        Player player = new Player("KPJ");
        assertThat(player.currentFrame()).isEqualTo(1);
        Frame normalFrame = new Frame();
        normalFrame.addFrame(new Score(9), false);
        normalFrame.addFrame(new Score(0), false);
        player.getFrames().addFrame(normalFrame);
        assertThat(player.currentFrame()).isEqualTo(2);
    }

    @Test
    @DisplayName("다음프레임던질지확인테스트")
    public void nextFrameTest() {
        Player player = new Player("KPJ");
        assertThat(player.currentFrame()).isEqualTo(1);
        Frame frame = player.getFrames().getFrame();
        frame.addFrame(new Score(9), false);
        player.getFrames().addFrame(frame);
        assertThat(player.getFrames().getFrames().get(0).getState().isFinish()).isFalse(); // 스트라이크 아니여서 한번더
        frame.addFrame(new Score(0), false);
        player.getFrames().addFrame(frame);
        assertThat(player.getFrames().getFrames().get(0).getState().isFinish()).isTrue(); // 2번다던져서 다음프레임으로
    }

    @Test
    @DisplayName("다음프레임던질지확인테스트(스트라이크)")
    public void nextFrameStrikeTest() {
        Player player = new Player("KPJ");
        assertThat(player.currentFrame()).isEqualTo(1);
        Frame frame = new Frame(); // 스트라이크여서 다음프레임으로
        frame.addFrame(new Score(10), false);
        player.getFrames().addFrame(frame);
        assertThat(player.getFrames().getFrames().get(0).getState().isFinish()).isTrue();
    }
}
