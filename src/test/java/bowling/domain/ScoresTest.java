package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoresTest {

    @Test
    void createTest() {
        Scores scores = new Scores();

        assertThat(scores.first()).isNull();
        assertThat(scores.second()).isNull();
    }

    @DisplayName("첫번째 두번째 볼을 굴려 맞춘 개수가 맞다.")
    @Test
    void hitTest() {
        Scores scores = new Scores();

        scores.hit(4);
        scores.hit(6);
    }

    @DisplayName("점수가 이미 다 입력되어있으면 두번 다 친 것이다.")
    @Test
    void isHitTwiceTest() {
        Scores scores = new Scores();

        scores.hit(4);
        scores.hit(6);

        assertThat(scores.isHitTwice()).isTrue();
    }

}
