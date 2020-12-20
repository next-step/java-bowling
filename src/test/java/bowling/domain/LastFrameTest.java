package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static bowling.domain.Scoring.NONE;
import static bowling.domain.Scoring.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * 마지막 프레임
 * <p>
 * * 마지막 프래임은 1..3 개의 투구를 가진다
 * [ ] ~마지막 프레임은 아홉번째 프레임이 스트라이크 인 경우 한번 더 투구 할 수 있다~
 * [x] 마지막 프레임은 첫번째 투구가 스트라이크 인 경우 두번 더 투구할 수 있다.
 * * 마지막 프레임은 더이상 투구할 수 없으면 종료한다.
 */
public class LastFrameTest {

    private Frame lastFrame;

    @BeforeEach
    void setUp() {
        lastFrame = new NormalFrame(9).throwBall(10);
    }

    @DisplayName("9번째 프레임이 끝나면 LastFrame 을 리턴한다")
    @Test
    void ninthFrameNext() {
        assertAll(
                // 스트라이크
                () -> assertThat(new NormalFrame(9).throwBall(10))
                        .isInstanceOf(LastFrame.class),
                // 스페어
                () -> assertThat(new NormalFrame(9).throwBall(2).throwBall(8))
                        .isInstanceOf(LastFrame.class),
                // 미스
                () -> assertThat(new NormalFrame(9).throwBall(2).throwBall(7))
                        .isInstanceOf(LastFrame.class),
                // 거터
                () -> assertThat(new NormalFrame(9).throwBall(0).throwBall(0))
                        .isInstanceOf(LastFrame.class)
        );
    }

    @DisplayName("마지막 프레임은 첫번째 투구가 스트라이크 인 경우 두번 더 투구할 수 있다")
    @Test
    void lastFrame3TimesThrow() {
        Frame first = lastFrame.throwBall(10);
        assertThat(first.getScoring()).isEqualTo(STRIKE);
        Frame second = first.throwBall(10);
        assertThat(second.getScoring()).isEqualTo(STRIKE);
        Frame third = first.throwBall(10);
        assertThat(third.getScoring()).isEqualTo(STRIKE);
    }

    @DisplayName("마지막 프레임의 미스, 스페어, 거터")
    @ParameterizedTest
    @CsvSource({"8,1,MISS", "8,2,SPARE", "0,0,MISS"})
    void lastFrameScoring(int firstThrow, int secondThrow, String scoringName) {
        assertThat(lastFrame.throwBall(firstThrow).throwBall(secondThrow).getScoring())
                .isEqualTo(Scoring.valueOf(scoringName));
    }

    @DisplayName("프레임이 끝나지 않으면 반환할 상태가 없다")
    @Test
    void incomplete() {
        assertThat(lastFrame.getScoring()).isEqualTo(NONE);
        lastFrame.throwBall(0);
        assertThat(lastFrame.getScoring()).isEqualTo(NONE);
    }

    @DisplayName("프레임이 끝나지 않으면 반환할 상태가 없다 - 첫 투구가 스트라이크인경우")
    @Test
    void incomplete2() {
        lastFrame.throwBall(10);
        lastFrame.throwBall(0);
        assertThat(lastFrame.getScoring()).isEqualTo(NONE);
    }

    @DisplayName("첫번째 스트라이크인 상태에서 미스, 스페어, 거터")
    @ParameterizedTest
    @CsvSource({"8,1,MISS", "8,2,SPARE", "0,0,MISS"})
    void lastFrameFirstStrikeAndScoring(int firstThrow, int secondThrow, String scoringName) {
        lastFrame.throwBall(10);
        assertThat(lastFrame.throwBall(firstThrow).throwBall(secondThrow).getScoring())
                .isEqualTo(Scoring.valueOf(scoringName));
    }

    @DisplayName("스페어 상태에서 한번 더 던지기")
    @Test
    void spareAndLastThrow() {
        lastFrame.throwBall(8).throwBall(2);
        assertThat(lastFrame.throwBall(10).getScoring()).isEqualTo(STRIKE);
    }

    @DisplayName("미스는 세번째 던지기를 할 수 없다")
    @Test
    void missAndLastThrow() {
        lastFrame.throwBall(8).throwBall(1);
        assertThatThrownBy(() -> lastFrame.throwBall(10))
                .isInstanceOf(IllegalBallThrownException.class);
    }

    @DisplayName("종료 케이스 10 / 10 / 10")
    @Test
    void finish_1() {
        assertThat(lastFrame.isFinish()).isFalse();
        lastFrame.throwBall(10);
        assertThat(lastFrame.isFinish()).isFalse();
        lastFrame.throwBall(10);
        assertThat(lastFrame.isFinish()).isFalse();
        lastFrame.throwBall(10);
        assertThat(lastFrame.isFinish()).isTrue();
    }

    @DisplayName("종료 케이스 9 / 1 / 10")
    @Test
    void finish_2() {
        assertThat(lastFrame.isFinish()).isFalse();
        lastFrame.throwBall(9);
        assertThat(lastFrame.isFinish()).isFalse();
        lastFrame.throwBall(1);
        assertThat(lastFrame.isFinish()).isFalse();
        lastFrame.throwBall(10);
        assertThat(lastFrame.isFinish()).isTrue();
    }

    @DisplayName("종료 케이스 1 / 1")
    @Test
    void finish_3() {
        assertThat(lastFrame.isFinish()).isFalse();
        lastFrame.throwBall(1);
        assertThat(lastFrame.isFinish()).isFalse();
        lastFrame.throwBall(1);
        assertThat(lastFrame.isFinish()).isTrue();
    }

    @Test
    void scoreMiss() {
        lastFrame.throwBall(8);
        assertThat(lastFrame.getScore().toInt()).isNull();
        lastFrame.throwBall(1);
        assertThat(lastFrame.getScore().toInt()).isEqualTo(9);
    }

    @Test
    void scoreSpare() {
        lastFrame.throwBall(8);
        assertThat(lastFrame.getScore().toInt()).isNull();
        lastFrame.throwBall(2);
        assertThat(lastFrame.getScore().toInt()).isNull();
        lastFrame.throwBall(8);
        assertThat(lastFrame.getScore().toInt()).isEqualTo(18);
    }

    @Test
    void scoreStrike() {
        lastFrame.throwBall(10);
        assertThat(lastFrame.getScore().toInt()).isNull();
        lastFrame.throwBall(2);
        assertThat(lastFrame.getScore().toInt()).isNull();
        lastFrame.throwBall(8);
        assertThat(lastFrame.getScore().toInt()).isEqualTo(20);
    }

}
