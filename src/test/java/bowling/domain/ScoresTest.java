package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoresTest {

    @Test
    @DisplayName("프레임의 결과 점수 리스트를 담는 객체를 생성한다.")
    public void create() throws Exception {
        Frames frames = new Frames();
        Scores scores = new Scores(frames.scores().scores());
        assertThat(scores.scores()).hasSize(10);
    }
}
