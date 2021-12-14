package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest() {
        assertThat(Score.of(0, 2)).isEqualTo(Score.of(0, 2));
    }

    @DisplayName("canCalucateScore 는 left 의 횟수가 0이면 true  0보다 크면 false를 반환한다.")
    @Test
    void isCalculateTest() {
        assertThat(Score.of(0, 2).canCalculateScore()).isFalse();
        assertThat(Score.of(0, 0).canCalculateScore()).isTrue();
    }

    @DisplayName("strike() 는 10점 에 추가 2번의 기회를 가진다.")
    @Test
    void strikeTest() {
        assertThat(Score.strike()).isEqualTo(Score.of(10, 2));
    }

    @DisplayName("spare() 는 10점 에 추가 1번의 기회를 가진다.")
    @Test
    void spareTest() {
        assertThat(Score.spare()).isEqualTo(Score.of(10, 1));
    }

    @DisplayName("miss(Pin, Pin) Pin value의 합과 추가 0번의 기회를 가진다.")
    @Test
    void missTest() {
        assertThat(Score.miss(Pin.from(5), Pin.from(3))).isEqualTo(Score.of(8, 0));
    }

    @DisplayName("addScore(Score) score의 값을 더하고 left를 -1한다.")
    @Test
    void addScoreTest() {
        assertThat(Score.strike().addScore(Score.of(5, 2))).isEqualTo(Score.of(15, 1));
    }
}
