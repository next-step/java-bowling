package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    private NormalFrame normalFrame;
    private NormalFrame strikeFrame;
    private NormalFrame spareFrame;

    @BeforeEach
    void setUp() {
        normalFrame = new NormalFrame();
        strikeFrame = new NormalFrame();
        strikeFrame.addPinCount(10);

        spareFrame = new NormalFrame();
        spareFrame.addPinCount(8);
        spareFrame.addPinCount(2);
    }

    @DisplayName("프레임의 쓰러트린 핀 갯수를 저장할 수 있다.")
    @Test
    void init() {
        assertThat(normalFrame.addPinCount(8)).isTrue();
        assertThat(normalFrame.addPinCount(1)).isTrue();

        Optional<Integer> score = normalFrame.getScore();
        assertThat(score.isPresent()).isTrue();
        assertThat(score.get()).isEqualTo(9);
    }

    @DisplayName("한 프레임에서 두번 이상 투구할 수 없다.")
    @Test
    void error() {
        assertThat(normalFrame.addPinCount(1)).isTrue();
        assertThat(normalFrame.addPinCount(1)).isTrue();
        assertThat(normalFrame.addPinCount(1)).isFalse();
    }

    @DisplayName("2회 시도하면 한 프레임은 끝이 난다.")
    @Test
    void finish() {
        normalFrame.addPinCount(8);
        assertThat(normalFrame.isDone()).isFalse();
        normalFrame.addPinCount(1);
        assertThat(normalFrame.isDone()).isTrue();
    }

    @DisplayName("스트라이크 치면 프레임은 끝난다.")
    @Test
    void finishAfterStrike() {
        assertThat(strikeFrame.isDone()).isTrue();
    }

    @DisplayName("스트라이크는 다음 2번의 투구까지 점수를 합산해야 한다. ")
    @Test
    void sumScoreWithTwoPitch() {
        Frame nextFrame = strikeFrame.createNext();

        nextFrame.addPinCount(8);

        assertThat(strikeFrame.getScore().isPresent()).isFalse();
        nextFrame.addPinCount(1);

        Optional<Integer> score = strikeFrame.getScore();
        assertThat(score.isPresent()).isTrue();
        assertThat(score.get()).isEqualTo(19);
    }

    @DisplayName("더블일 경우 그 다음 프레임의 첫 점수까지 합산한다")
    @Test
    void sumScoreWithTowPitchEvenDouble() {
        Frame nextFrame = strikeFrame.createNext();
        Frame afterNextFrame = nextFrame.createNext();

        assertThat(strikeFrame.getScore().isPresent()).isFalse();

        nextFrame.addPinCount(10);
        assertThat(strikeFrame.getScore().isPresent()).isFalse();

        afterNextFrame.addPinCount(1);

        Optional<Integer> score = strikeFrame.getScore();
        assertThat(score.isPresent()).isTrue();
        assertThat(score.get()).isEqualTo(21);
    }

    @DisplayName("스페어는 다음 1번의 투구까지 점수를 합산해야 한다. ")
    @Test
    void sumScoreWithOnePitch() {
        Frame nextFrame = spareFrame.createNext();
        assertThat(spareFrame.getScore().isPresent()).isFalse();
        nextFrame.addPinCount(8);

        Optional<Integer> score = spareFrame.getScore();
        assertThat(score.isPresent()).isTrue();
        assertThat(score.get()).isEqualTo(18);
    }
}
