package bowling.domain.bowling;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("볼링 일급 컬렉션 테스트")
public class BowlingsTest {
    @DisplayName("모든 볼링이 종료되었는지 확인")
    @ParameterizedTest
    @MethodSource("getBowlings")
    public void isFinished(Bowlings bowlings, boolean expectedResult) {
        assertThat(bowlings.isFinished()).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> getBowlings() {

        return Stream.of(
                Arguments.arguments(
                        Bowlings.of(Arrays.asList(
                                Bowling.of("PJS", Frames.create().getFrames()),
                                Bowling.of("KYJ", Frames.create().getFrames()))), false
                ),
                Arguments.arguments(
                        Bowlings.of(Arrays.asList(
                                Bowling.of("PJS", getFinishedFrames()),
                                Bowling.of("KYJ", getFinishedFrames()))), true
                )
        );
    }

    private static List<Frame> getFinishedFrames() {
        Frames frames = Frames.create();
        for (int i = 0; i < 12; i++) {
            frames.record(Pin.of(10));
        }
        return frames.getFrames();
    }

    @DisplayName("투구한 후에 다음 차례인 플레이어의 이름 가져오기")
    @ParameterizedTest
    @CsvSource(value = {"3:PJS", "10:KYJ"}, delimiter = ':')
    public void currentMemberName(int pins, String expectedCurrentName) {
        Bowlings bowlings = Bowlings.of(Arrays.asList(
                Bowling.of("PJS", Frames.create().getFrames()),
                Bowling.of("KYJ", Frames.create().getFrames())));

        bowlings.throwCurrentMemberBall(Pin.of(pins));

        assertThat(bowlings.getCurrentMemberName()).isEqualTo(expectedCurrentName);
    }
}