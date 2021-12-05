package bowling.domain.value;

import bowling.domain.factory.FrameFactory;
import bowling.domain.frame.Frame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BowlingClubTest {
    private BowlingClub bowlingClub;

    @BeforeEach
    void setup() {
        FrameFactory frameFactory = new FrameFactory();
        List<Frame> frames = frameFactory.create();

        bowlingClub = BowlingClub.from(frames);
    }

    @Test
    @DisplayName("기본 프레임에서 스트라이크가 발생되면 다음 프레임 넘어가는 부분 검증")
    void knockedDown() {
        bowlingClub.knockedDown(Pins.from(10));

        assertThat(bowlingClub.getCurrentFrameNumber()).isEqualTo(FrameNumber.from(2));
    }

    @Test
    @DisplayName("기본 프레임에서 투구수 2회 발생되면 다음 프레임 넘어가는 부분 검증")
    void knockedDown2() {
        bowlingClub.knockedDown(Pins.from(4));
        bowlingClub.knockedDown(Pins.from(3));

        assertThat(bowlingClub.getCurrentFrameNumber()).isEqualTo(FrameNumber.from(2));
    }

    @Test
    @DisplayName("기본 프레임에서 스트라이크가 발생되지 않고 1회 투구 인 경우, 기존 프레임 유지 검증")
    void knockedDown3() {
        bowlingClub.knockedDown(Pins.from(4));

        assertThat(bowlingClub.getCurrentFrameNumber()).isEqualTo(FrameNumber.from(1));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "   4|      5",
            "   4|      0"
    }, delimiter = '|')
    @DisplayName("마지막 프레임에서 미스/거터가 발생되는 경우 정상 종료 확인")
    void isGameOver(int firstPitch, int secondPitch) {
        testNormalFrame();

        assertThat(bowlingClub.isGameOver()).isFalse();

        bowlingClub.knockedDown(Pins.from(firstPitch));
        bowlingClub.knockedDown(Pins.from(secondPitch));

        assertThat(bowlingClub.isGameOver()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "   4|      6|      4",
            "   5|      5|     10",
            "  10|      0|      5",
            "  10|      0|     10",
            "  10|     10|      5",
            "  10|     10|     10"
    }, delimiter = '|')
    @DisplayName("마지막 프레임에서 투구수 3회 발생되는 경우(스페어, 스트라이크) 정상 종료 확인")
    void isGameOver2(int firstPitch, int secondPitch, int bonusPitch) {
        testNormalFrame();

        assertThat(bowlingClub.isGameOver()).isFalse();

        bowlingClub.knockedDown(Pins.from(firstPitch));
        bowlingClub.knockedDown(Pins.from(secondPitch));
        bowlingClub.knockedDown(Pins.from(bonusPitch));

        assertThat(bowlingClub.isGameOver()).isTrue();
    }

    private void testNormalFrame() {
        for (int i = 1; i < 10; i++) {
            bowlingClub.knockedDown(Pins.from(10));
        }
    }

    @Test
    @DisplayName("기본 프레임에서 투구의 합이 10핀이 넘어가는 경우 예외 발생")
    void knockedDown_exception() {
        bowlingClub.knockedDown(Pins.from(4));
        assertThatIllegalArgumentException().isThrownBy(() -> bowlingClub.knockedDown(Pins.from(7)));
    }

    @Test
    @DisplayName("마지막 프레임에서 두번째 투구의 합이 10핀이 넘어가는 경우 예외 발생")
    void knockedDown_exception2() {
        testNormalFrame();

        bowlingClub.knockedDown(Pins.from(4));
        assertThatIllegalArgumentException().isThrownBy(() -> bowlingClub.knockedDown(Pins.from(7)));
    }

    @Test
    @DisplayName("스트라이크는 다음 2번의 투구까지 점수를 누적해서 합산 검증")
    void getScore() {
        bowlingClub.knockedDown(Pins.from(10));
        assertThat(bowlingClub.getScore(1)).isEmpty();

        bowlingClub.knockedDown(Pins.from(10));
        assertThat(bowlingClub.getScore(1)).isEmpty();

        bowlingClub.knockedDown(Pins.from(10));
        assertThat(bowlingClub.getScore(1)).isEqualTo("30");
    }

    @Test
    @DisplayName("스페어는 다음 1번의 투구까지 점수를 누적해서 합산 검증")
    void getScore2() {
        bowlingClub.knockedDown(Pins.from(5));
        assertThat(bowlingClub.getScore(1)).isEmpty();

        bowlingClub.knockedDown(Pins.from(5));
        assertThat(bowlingClub.getScore(1)).isEmpty();

        bowlingClub.knockedDown(Pins.from(10));
        assertThat(bowlingClub.getScore(1)).isEqualTo("20");
    }

    @Test
    @DisplayName("미스는 현재 프레임의 투구 합산 검증")
    void getScore3() {
        bowlingClub.knockedDown(Pins.from(5));
        assertThat(bowlingClub.getScore(1)).isEmpty();

        bowlingClub.knockedDown(Pins.from(3));
        assertThat(bowlingClub.getScore(1)).isEqualTo("8");
    }
}
