package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.BDDAssertions.then;

public class NormalFrameTest {


    @Test
    @DisplayName("초구를 던지지 않았을 경우, 초구를 던진다.")
    public void throwBowl_first() throws Exception {
        Frame normalFrame = new NormalFrame(0).throwBowl("1");
        assertThat(normalFrame.pinCounts().pinCounts().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("초구를 던졌을 경우, 2구를 던진다.")
    public void throwBowl_second() throws Exception {
        Frame firstThrown = new NormalFrame(0).throwBowl("1");
        Frame secondThrown = firstThrown.throwBowl("2");
        assertThat(secondThrown.pinCounts().pinCounts().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("3구를 던지려 할 경우, 예외가 발생한다.")
    public void throwBowl_exception() throws Exception {
        Frame secondThrown = new NormalFrame(0).throwBowl("1").throwBowl("2");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> secondThrown.throwBowl("1"))
                .withMessage("2번을 초과하여 던질 수 없습니다.");
    }

    @Test
    @DisplayName("해당 프레임이 완료되었을 경우, 다음 프레임을 반환한다.")
    public void next() throws Exception {
        //given
        Frame lastBeforeTheFinal = new NormalFrame(8).throwBowl("1");
        Frame normal = new NormalFrame(7).throwBowl("1");

        //when
        Frame finalFrame = lastBeforeTheFinal.throwBowl("2").next();
        Frame normalFrame = normal.throwBowl("2").next();

        then(finalFrame).isInstanceOf(FinalFrame.class);
        then(normalFrame).isInstanceOf(NormalFrame.class);
    }

    @Test
    @DisplayName("해당 프레임이 완료되지 않았을 경우, 현재 프레임을 반환한다.")
    public void next_same_frame() throws Exception {
        Frame frame = new NormalFrame(8).throwBowl("1").next();
        assertThat(frame.index()).isEqualTo(8);
    }

    @Test
    @DisplayName("해당 프레임이 완료됐을 경우, 참을 반환한다.")
    public void isFinished() throws Exception {
        Frame firstThrown = new NormalFrame(0).throwBowl("1");
        Frame secondThrown = firstThrown.throwBowl("2");
        assertThat(secondThrown.isFinished()).isTrue();
    }

    @Test
    @DisplayName("해당 프레임이 완료되지 않았을 경우, 카운트 할 수 없는 점수를 반환한다.")
    public void score_unCountableScore() throws Exception {
        Frame notFinishedFrame = new NormalFrame(0).throwBowl("1");
        Score score = notFinishedFrame.score();
        assertThat(score).isEqualTo(Score.unCountableScore());
    }

    @Test
    @DisplayName("해당 프레임이 완료 및 초구 스트라이크일 경우, 점수를 2번 더할 수 있는 기회를 가지는 점수를 반환한다.")
    public void score_Strike() throws Exception {
        Frame strikeFrame = new NormalFrame(0).throwBowl("10");
        Score score = strikeFrame.score();
        assertThat(score).isEqualTo(Score.Strike(10));
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 9", "2, 8", "3, 7", "0, 10"})
    @DisplayName("해당 프레임이 완료 및 2구 스페어일 경우, 점수를 1번 더할 수 있는 기회를 가지는 점수를 반환한다.")
    public void score_Spare(String firstPinCount, String secondPinCount) throws Exception {
        Frame spareFrame = new NormalFrame(0).throwBowl(firstPinCount).throwBowl(secondPinCount);
        Score score = spareFrame.score();
        assertThat(score).isEqualTo(Score.Spare(10));
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 8", "2, 7", "0, 1", "3, 4"})
    @DisplayName("해당 프레임이 완료됐지만 스트라이크 또는 스페어가 아닐 경우, 점수를 더할 수 있는 기회가 없는 점수를 반환한다.")
    public void score_Miss(String firstPinCount, String secondPinCount) throws Exception {
        Frame missFrame = new NormalFrame(0).throwBowl(firstPinCount).throwBowl(secondPinCount);
        Score score = missFrame.score();
        assertThat(score).isEqualTo(Score.Miss(Integer.parseInt(firstPinCount) + Integer.parseInt(secondPinCount)));
    }

    @Test
    @DisplayName("프레임이 한 번도 플레이 되지 않은 경우, 카운트 할 수 없는 점수를 반환한다.")
    public void add_with_empty_pinCount() throws Exception {
        Frame notThrownFrame = new NormalFrame(0);
        Score score = notThrownFrame.add(Score.Strike(10));
        assertThat(score).isEqualTo(Score.unCountableScore());
    }

    @ParameterizedTest
    @CsvSource(value = {"0", "9"})
    @DisplayName("점수를 2번 더할 수 있는 점수가 인자로 들어오지만, 현재 프레임이 한 번만 플레이 되었고 스트라이크가 아닐 경우, 카운트 할 수 없는 점수를 반환한다.")
    public void add_score_with_two_opportunities_fail(String firstPinCount) throws Exception {
        Frame firstThownFrame = new NormalFrame(0).throwBowl(firstPinCount);
        Score score = firstThownFrame.add(Score.Strike(10));
        assertThat(score).isEqualTo(Score.unCountableScore());
    }

    @Test
    @DisplayName("점수를 2번 더할 수 있는 점수가 인자로 들어오고, 현재 프레임 초구가 스트라이크일 경우, 1번 더 더할 수 있는 기회를 가진 점수(현재 프레임 초구 + 인자로 받은 점수)를 반환한다.")
    public void add_score_with_two_opportunities() throws Exception {
        Frame firstThownFrame = new NormalFrame(0).throwBowl("10");
        Score score = firstThownFrame.add(Score.Strike(10));
        assertThat(score).isEqualTo(Score.Spare(20));
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 8", "2, 7", "3, 6", "4, 5"})
    @DisplayName("점수를 2번 더할 수 있는 점수가 인자로 들어오고, 현재 프레임이 두 번 모두 플레이가 되었다면, 최종 점수(현재 프레임 초구, 2구 점수 + 인자로 받은 점수)를 반환한다.")
    public void add_score_with_two_opportunities(String firstPinCount, String secondPinCount) throws Exception {
        Frame frame = new NormalFrame(0).throwBowl(firstPinCount).throwBowl(secondPinCount);
        Score score = frame.add(Score.Strike(10));
        assertThat(score).isEqualTo(Score.Miss(10 + Integer.parseInt(firstPinCount) + Integer.parseInt(secondPinCount)));
    }

    @ParameterizedTest
    @CsvSource(value = {"0", "9"})
    @DisplayName("점수를 1번 더할 수 있는 점수가 인자로 들어오고, 현재 프레임이 한 번 이상 플레이가 되었다면, 최종 점수(현재 프레임 초구 점수 + 인자로 받은 점수)를 반환한다.")
    public void add_score_with_one_opportunity(String firstPinCount) throws Exception {
        Frame frame = new NormalFrame(0).throwBowl(firstPinCount);
        Score score = frame.add(Score.Spare(10));
        assertThat(score).isEqualTo(Score.Miss(10 + Integer.parseInt(firstPinCount)));
    }
}
