package bowling.domain.frame.status;

import bowling.bowlingexception.InvalidScoreCalculationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OnSecondPitchTest {

    @Test
    @DisplayName("출력 확인")
    void getDescription() {
        OnSecondPitch onSecond = new OnSecondPitch(5);

        assertThat(onSecond.getDescription())
                .isEqualTo("5");
    }

    @Test
    @DisplayName("추가 피치에 따른 반환 확인(스페어)")
    void recordWhenSpare() {
        OnSecondPitch onSecondPitch = new OnSecondPitch(5);

        assertThat(onSecondPitch.record(5))
                .isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("추가 피치에 따른 반환 확인(미스)")
    void recordWhenMiss() {
        OnSecondPitch onSecondPitch = new OnSecondPitch(5);

        assertThat(onSecondPitch.record(4))
                .isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("OnSecondPitch 에서 스코어 반환 요청에 대한 예외처리 테스트")
    void calculateScore() {

        OnSecondPitch secondPitch = new OnSecondPitch(4);

        assertThatThrownBy(
                secondPitch::calculateBaseScoreOfFrame
        ).isInstanceOf(InvalidScoreCalculationException.class);
    }
}
