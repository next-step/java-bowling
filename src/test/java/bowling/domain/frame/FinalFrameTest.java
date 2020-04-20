package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {
    private FinalFrame finalFrame;
    private NormalFrame normalFrame;

    @BeforeEach
    void setUp() {
        finalFrame = new FinalFrame();
        normalFrame = new NormalFrame();
    }


    @DisplayName("한 프레임에서 세번 이상 투구할 수 없다.")
    @Test
    void overThreePitch() {
        assertThat(finalFrame.addPinCount(10)).isTrue();
        assertThat(finalFrame.addPinCount(1)).isTrue();
        assertThat(finalFrame.addPinCount(1)).isTrue();
        assertThat(finalFrame.addPinCount(1)).isFalse();
    }

    @DisplayName("첫번째 시도가 스트라크일 경우, 두번째 핀의 갯수는 아무거나 관계 없다")
    @Test
    void secondPinAfterStrike() {
        finalFrame.addPinCount(10);
        finalFrame.addPinCount(8);
        finalFrame.addPinCount(1);

        Optional<Integer> score = finalFrame.getScore();
        assertThat(score.isPresent()).isTrue();
        assertThat(score.get()).isEqualTo(19);
    }

    @DisplayName("두번째 시도에서 스페어 처리를 못하면 세번째 기회는 없다.")
    @Test
    void donAtSecond() {
        finalFrame.addPinCount(8);
        finalFrame.addPinCount(1);
        assertThat(finalFrame.isDone()).isTrue();
    }

    @DisplayName("두번째 시도에서 스페어 처리 되면 한번의 기회를 얻는다")
    @Test
    void getThirdOpportunityWhenSpare() {
        finalFrame.addPinCount(8);
        finalFrame.addPinCount(2);
        assertThat(finalFrame.isDone()).isFalse();
        finalFrame.addPinCount(10);
        assertThat(finalFrame.isDone()).isTrue();
    }

    @DisplayName("첫번째 시도에서 스트라이크 되면 두번의 기회를 얻는다")
    @Test
    void getTwoOpportunityWhenStrike() {
        finalFrame.addPinCount(10);
        assertThat(finalFrame.isDone()).isFalse();
        finalFrame.addPinCount(2);
        assertThat(finalFrame.isDone()).isFalse();
        finalFrame.addPinCount(3);
        assertThat(finalFrame.isDone()).isTrue();
    }

    @DisplayName("모든 시도가 스트라이크여도 세번의 기회가 주어진다.")
    @Test
    void onlyThreeOpportunity() {
        finalFrame.addPinCount(10);
        assertThat(finalFrame.isDone()).isFalse();
        finalFrame.addPinCount(10);
        assertThat(finalFrame.isDone()).isFalse();
        finalFrame.addPinCount(10);
        assertThat(finalFrame.isDone()).isTrue();
    }

    @DisplayName("스트라이크는 다음 2번의 투구까지 점수를 합산해야 한다. ")
    @Test
    void strike() {
        Frame finalFrame = normalFrame.createNext(new FinalFrame());

        normalFrame.addPinCount(10);
        finalFrame.addPinCount(8);
        finalFrame.addPinCount(1);

        Optional<Integer> score = normalFrame.getScore();
        assertThat(score.isPresent()).isTrue();
        assertThat(score.get()).isEqualTo(19);
    }

    @DisplayName("스페어는 다음 1번의 투구까지 점수를 합산해야 한다. ")
    @Test
    void spare() {
        Frame finalFrame = normalFrame.createNext(new FinalFrame());

        normalFrame.addPinCount(8);
        normalFrame.addPinCount(2);

        finalFrame.addPinCount(8);
        finalFrame.addPinCount(1);

        Optional<Integer> score = normalFrame.getScore();
        assertThat(score.isPresent()).isTrue();
        assertThat(score.get()).isEqualTo(18);
    }

    @DisplayName("투구를 세번 하면, 세번 모두의 점수가 합산된다.")
    @Test
    void threeScore() {
        finalFrame.addPinCount(8);
        finalFrame.addPinCount(2);

        assertThat(finalFrame.getScore().isPresent()).isFalse();
        finalFrame.addPinCount(1);

        Optional<Integer> score = finalFrame.getScore();
        assertThat(score.isPresent()).isTrue();
        assertThat(score.get()).isEqualTo(11);
    }

    @DisplayName("세번째 기회를 얻지 못했을때는 두번의 투구만 합산한다.")
    @Test
    void twoScore() {
        finalFrame.addPinCount(8);
        finalFrame.addPinCount(1);


        Optional<Integer> score = finalFrame.getScore();
        assertThat(score.isPresent()).isTrue();
        assertThat(score.get()).isEqualTo(9);
    }
}
