package bowling;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.RandomGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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
        Frames tenFrames = Frame.createTenFrames(randomGenerator);

        //then
        assertThat(tenFrames.getFrames()).hasSize(10);
        for (int i = 0; i < 10; i++) {
            assertThat(tenFrames.getFrameId(i)).isEqualTo(i + 1);
        }
    }

    /*
    각 프레임의 첫 투구가 스트라이크면 두 번째 투구는 0으로 설정했기 때문에,
    본 코드에서는 10프레임의 첫 투구가 스트라이크일 경우,
    코드 상으로는 총 4회의 투구가 진행되며, 외관상으로는 3회의 투구가 진행되는 것처럼 보인다.

     */
    @DisplayName("10프레임이 스트라이크면, 투구 횟수 2회가 추가된 frame을 리턴한다.")
    @Test
    void createTenthFrameWhenTenthIsStrike() {
        //when
        Frame tenthFrame = new Frame(9, 10, 0);

        //then
        assertThat(tenthFrame.getPoints().getPointSize()).isEqualTo(4);
    }

    @DisplayName("10프레임이 스페어이면, 투구 횟수가 1회 추가된 frame을 리턴한다.")
    @ParameterizedTest
    @CsvSource(value = {"8:2", "9:1", "0:10"}, delimiter = ':')
    void createTenthFrameWhenTenthIsSpare(int firstPoint, int secondPoint) {
        //when
        Frame tenthFrame = new Frame(9, firstPoint, secondPoint);

        //then
        assertThat(tenthFrame.getPoints().getPointSize()).isEqualTo(3);
    }
}