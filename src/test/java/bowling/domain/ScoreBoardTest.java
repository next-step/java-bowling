package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ScoreBoardTest {

    @Test
    @DisplayName("점수판 생성 테스트")
    void init() {
        assertThatCode(() -> ScoreBoard.init()).doesNotThrowAnyException();


    }


    @Test
    @DisplayName("점수판 프레임 생성 확인")
    void init_frame() {
        ScoreBoard scoreBoard = ScoreBoard.init();

        assertThat(scoreBoard.size()).isEqualTo(10);
        assertThat(scoreBoard.getCurrentIndex()).isEqualTo(0);
    }

}
