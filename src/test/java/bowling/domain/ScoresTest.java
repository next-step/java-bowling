package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoresTest {

    private Scores scores;

    @BeforeEach
    void setUp() {
        scores = new Scores();
    }

    @Test
    void createTest() {
        assertThat(scores.first()).isNull();
        assertThat(scores.second()).isNull();
    }

    @DisplayName("첫번째와 두번째 볼을 굴려서 맞춘 개수 점수는 각각 저장된다.")
    @Test
    void hitTest() {
        scores.hit(4);
        scores.hit(6);

        assertThat(scores.first()).isEqualTo(new Score(4));
        assertThat(scores.second()).isEqualTo(new Score(6));
    }

    @DisplayName("점수가 이미 다 입력되어있으면 두번 다 친 것이다.")
    @Test
    void isPlayTwiceTest() {
        scores.hit(4);
        assertThat(scores.isPlayTwice()).isFalse();
        scores.hit(6);
        assertThat(scores.isPlayTwice()).isTrue();
    }

    @Test
    void isStrikeTest() {
        scores.hit(10);

        assertThat(scores.isStrike()).isTrue();
    }

    @Test
    void isSpareTest() {
        scores.hit(4);
        scores.hit(6);

        assertThat(scores.isSpare()).isTrue();
    }

}
