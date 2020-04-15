package bowling.domain.frame;

import bowling.domain.frame.FrameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameResultTest {
    @DisplayName("첫번째 포인트가 10, 두번째가 0일 때 스트라이크를 반환")
    @Test
    void findFrameResultWhenStrike() {
        //when
        FrameResult result = FrameResult.findResult(10, 0);

        //then
        assertThat(result).isEqualTo(FrameResult.STRIKE);
    }

    @DisplayName("스트라이크가 아닐 떄, 첫번째 포인트와 두번째 포인트의 합이 10이면 스페어 반환")
    @ParameterizedTest
    @CsvSource(value = {"8:2", "9:1", "0:10"}, delimiter = ':')
    void returnWhenSumIsTenAndNotStrike(int firstPoint, int secondPoint) {
        //when
        FrameResult result = FrameResult.findResult(firstPoint, secondPoint);

        //then
        assertThat(result).isEqualTo(FrameResult.SPARE);
    }

    @DisplayName("첫번째, 두번째 포인트 합이 0이면 거터 반환")
    @Test
    void findFrameResultWhenGutter() {
        //when
        FrameResult result = FrameResult.findResult(0, 0);

        assertThat(result).isEqualTo(FrameResult.GUTTER);
    }

    @DisplayName("첫번째, 두번째 합이 10 미만, 0 초과일 때 미스 반환")
    @ParameterizedTest
    @CsvSource(value = {"5:2", "7:1", "9:0", "0:1"}, delimiter = ':')
    void findFrameResultWhenMiss(int firstPoint, int secondPoint) {
        //when
        FrameResult result = FrameResult.findResult(firstPoint, secondPoint);

        //then
        assertThat(result).isEqualTo(FrameResult.MISS);
    }
}