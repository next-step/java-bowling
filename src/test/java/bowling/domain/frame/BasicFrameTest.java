package bowling.domain.frame;

import bowling.domain.BowlType;
import bowling.domain.point.Point;
import bowling.domain.score.ScoreDto;
import bowling.exception.NotHasTurnException;
import bowling.exception.ValidOverPointException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BasicFrameTest {

    private Frame basicFrame;
    private Frame nextBasicFrame;

    @BeforeEach
    void setUp() {
        basicFrame = BasicFrame.initFirst();
        nextBasicFrame = basicFrame.createNextFrame(false);
    }

    @Test
    @DisplayName("베이직 프레임에서 미스 피칭을 한다.")
    void pitchMiss() {
        basicFrame.pitch(Point.valueOf(1));
        basicFrame.pitch(Point.valueOf(2));

        assertThat(basicFrame.getScoreDto()).isEqualToComparingFieldByField(ScoreDto.init(3, BowlType.END));
    }

    @Test
    @DisplayName("현재 프레임에서 피칭을 Strike했을경우 다음 피칭까지 기다린다.")
    void pitchStrike() {
        basicFrame.pitch(Point.valueOf(10));
        assertThat(basicFrame.getScoreDto().getSumScore()).isEqualTo(0);
        assertThat(basicFrame.getScoreDto().getBowlType()).isEqualTo(BowlType.NONE);
    }

    @Test
    @DisplayName("현재 프레임에서 피칭을 Spare했을경우 다음 피칭에 점수를 합산해 준다.")
    void pitchStrikeNextTurnPitch() {
        basicFrame.pitch(Point.valueOf(2));
        basicFrame.pitch(Point.valueOf(8));
        nextBasicFrame.pitch(Point.valueOf(3));
        assertThat(basicFrame.getScoreDto().getSumScore()).isEqualTo(13);
        assertThat(basicFrame.getScoreDto().getBowlType()).isEqualTo(BowlType.END);
    }

    @Test
    @DisplayName("스트라이크_하고_한번더_던지게_되면_익셉션이발생한다.")
    void 스트라이크_하고_한번더_던지게_되면_익셉션이발생한다() {

        basicFrame.pitch(Point.valueOf(9));
        assertThatThrownBy(() -> basicFrame.pitch(Point.valueOf(2)))
                .isInstanceOf(ValidOverPointException.class);
    }


    @Test
    @DisplayName("횟수를 초과하면 익셉션이 발생한다.")
    void throwCountException() {

        basicFrame.pitch(Point.valueOf(10));
        assertThatThrownBy(() -> basicFrame.pitch(Point.valueOf(2)))
                .isInstanceOf(NotHasTurnException.class);
    }


}