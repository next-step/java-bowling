package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.score.FinalScore;
import bowling.domain.score.NormalScore;
import bowling.domain.score.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    @DisplayName("첫번째 프레임을 생성할 수 있다.")
    void createFirstFrameTest() {

        // given
        Frame expected = NormalFrame.of(1, NormalScore.empty(), null);

        // when
        Frame result = NormalFrame.createFirstFrame();

        // then
        assertThat(result).isEqualTo(expected);
    }
    
    @Test
    @DisplayName("현재 프레임을 가지고 다음 프레임을 생성할 수 있다.")
    void createNextFrameTest() {
        
        // givne
        Frame first = NormalFrame.createFirstFrame();
        Frame expected = NormalFrame.of(2, NormalScore.empty(), null);
        
        // when
        Frame result = first.createNextFrame();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("9번 프레임에서는 다음 프레임을 10번 프레임을 생성해야 한다.")
    void createFinalFrameTest() {

        // given
        Frame input = NormalFrame.of(9, NormalScore.empty(), null);
        Frame expected = FinalFrame.of(10, FinalScore.empty());

        // when
        Frame result = input.createNextFrame();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("새로운 핀 정보를 받아서 현재 Frame의 score에 저장할 수 있다.")
    void saveScoreByPinTest() {

        // given
        Pin pin = Pin.of(7);
        Frame result = NormalFrame.createFirstFrame();

        // when
        result.updateScoreByPin(pin);

        // then
        assertThat(result).isInstanceOf(Frame.class);
        assertThat(result).isInstanceOf(NormalFrame.class);
    }

    @Test
    @DisplayName("새로운 핀 정보를 받아서 스페어 처리를 할 수 있다.")
    void saveScoreByPinSpareTest() {

        // given
        Pin pin = Pin.of(7);
        Frame result = NormalFrame.createFirstFrame();
        result.updateScoreByPin(pin);
        Pin second = Pin.of(3);

        // when
        result.updateScoreByPin(second);

        // then
        assertThat(result).isInstanceOf(Frame.class);
        assertThat(result).isInstanceOf(NormalFrame.class);
    }

    @Nested
    class getNextFrameTest {

        @Test
        @DisplayName("연결된 Frame이 null이 아니면 해당 Frame을 가져온다")
        void notNullTest() {

            // given
            Frame first = NormalFrame.createFirstFrame();
            Frame expected = first.nextFrame();

            // when
            Frame result = first.nextFrame();

            // then
            assertThat(result).isEqualTo(expected);
        }

        @Test
        @DisplayName("연결된 Frame이 없으면 null반환")
        void nullTest() {

            // given
            Frame first = NormalFrame.createFirstFrame();

            // when
            Frame result = first.nextFrame();

            // then
            assertThat(result).isNull();
        }

    }

}