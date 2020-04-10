package bowling;

import bowling.domain.Frame;
import bowling.domain.Ordinal;
import bowling.domain.RandomGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

public class FrameTest {
    @DisplayName("0이상 10 이하의 숫자 두 개로 Frame 객체 생성")
    @ParameterizedTest
    @CsvSource(value = {"0:10", "1:5", "8:2"}, delimiter = ':')
    void createTest(int firstPoint, int secondPoint) {
        assertThatCode(() -> {
            new Frame(firstPoint, secondPoint);
        }).doesNotThrowAnyException();
    }

    @DisplayName("첫번째 포인트가 10일 때, 두 번째 포인트가 0이 아니면 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    void throwExceptionWhenFirstScoreTenAndSecondNotZero(int secondPoint) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Frame(10, secondPoint);
        });
    }

    @DisplayName("첫번째 포인트와 두번째 포인트의 합이 10 초과이면 예외 발생")
    @ParameterizedTest
    @CsvSource(value = {"8:3", "9:2", "10:9"}, delimiter = ':')
    void throwExceptionWhenSumIsGreaterThanTen(int firstPoint, int secondPoint) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Frame(firstPoint, secondPoint);
        });
    }

    @DisplayName("RandomGenerator를 이용해 Frame 객체 생성")
    @Test
    void createWithRandomGenerator() {
        RandomGenerator randomGenerator = new RandomGenerator();

        IntStream.range(0, 1000)
                .forEach(it -> assertThatCode(() -> {
                    Frame.create(randomGenerator);
                }).doesNotThrowAnyException());
    }

    @DisplayName("next frame 생성할 때 frameId 1씩 증가하기")
    @Test
    void createNextFrame() {
        //given
        RandomGenerator randomGenerator = new RandomGenerator();
        Frame frame1 = Frame.create(randomGenerator);

        //when
        Frame frame2 = frame1.createNextFrame(randomGenerator);
        Frame frame3 = frame2.createNextFrame(randomGenerator);

        //then
        assertThat(frame2.getFrameId()).isEqualTo(frame1.getFrameId() + 1);
        assertThat(frame3.getFrameId()).isEqualTo(frame2.getFrameId() + 1);
    }

    @DisplayName("1부터 10까지 순차적으로 frameId로 가지는 10개의 frame 생성하기")
    @Test
    void createTenFrames() {
        //given
        RandomGenerator randomGenerator = new RandomGenerator();

        //when
        List<Frame> tenFrames = Frame.createTenFrames(randomGenerator);

        //then
        assertThat(tenFrames).hasSize(10);
        for (int i = 0; i < 10; i++) {
            assertThat(tenFrames.get(i).getFrameId()).isEqualTo(i + 1);
        }
    }

    @DisplayName("10프레임이 스페어 또는 스트라이크면, 투구 횟수 1회가 추가된 frame을 리턴한다.")
    @Test
    void createTenthFramesWhenTenthIsSpareOrStrike() {
        //given
        RandomGenerator randomGenerator = new RandomGenerator();
        int prevFrameId = 10;
        Frame tenthFrame = new Frame(prevFrameId, 10, 10);

        //when
        Frame nextFrame = tenthFrame.createNextFrame(randomGenerator);

        //then
        assertThat(nextFrame.getFrameId()).isEqualTo(10);
        assertThat(nextFrame.getPoints().getPointSize()).isEqualTo(3);
        assertThat(nextFrame.getPoints().getPoints().get(Ordinal.FIRST))
                .isEqualTo(tenthFrame.getPoints().getPoints().get(Ordinal.FIRST));
        assertThat(nextFrame.getPoints().getPoints().get(Ordinal.SECOND))
                .isEqualTo(tenthFrame.getPoints().getPoints().get(Ordinal.SECOND));
    }
}