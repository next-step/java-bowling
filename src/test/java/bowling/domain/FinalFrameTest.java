package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FinalFrameTest {

    @Test
    @DisplayName("초구를 던지지 않았을 경우, 초구를 던진다.")
    public void throwBowl_firstBowl() throws Exception {
        Frame frame = new FinalFrame().throwBowl("1");
        PinCounts pinCounts = frame.pinCounts();

        assertThat(pinCounts.pinCounts().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("초구를 던졌을 경우, 2구를 던진다.")
    public void throwBowl_secondBall() throws Exception {
        Frame firstThrown = new FinalFrame().throwBowl("1");
        Frame secondThrown = firstThrown.throwBowl("2");
        PinCounts pinCounts = secondThrown.pinCounts();

        assertThat(pinCounts.pinCounts().size()).isEqualTo(2);
    }

    @ParameterizedTest
    @CsvSource(value = {"10, 1, 1", "5, 5, 1", "0, 10 ,10"})
    @DisplayName("초구 스트라이크 또는 2구 스페어일 경우, 3구를 던진다.")
    public void throwBowl_thirdBowl(String firstPinCount, String secondPinCount, String thirdPinCount) throws Exception {
        Frame firstThrown = new FinalFrame().throwBowl(firstPinCount);
        Frame secondThrown = firstThrown.throwBowl(secondPinCount);
        Frame thirdThrown = secondThrown.throwBowl(thirdPinCount);
        PinCounts pinCounts = thirdThrown.pinCounts();

        assertThat(pinCounts.pinCounts().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("초구 스트라이크 또는 2구 스페어가 아닌 상황에서 3구를 던지려고 할 경우 예외가 발생한다.")
    public void throwBowl_thirdBowl() throws Exception {
        Frame firstThrown = new FinalFrame().throwBowl("5");
        FinalFrame secondThrown = (FinalFrame) firstThrown.throwBowl("4");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> secondThrown.throwBowl("1"))
                .withMessage("3구를 던질수 없습니다.");
    }

    @Test
    @DisplayName("다음 차례가 존재하지 않으므로, 자신을 반환한다.")
    public void next() throws Exception {
        //given
        Frame frame = new FinalFrame();
        assertThat(frame.next()).isEqualTo(frame);
    }

    @ParameterizedTest
    @CsvSource(value = {"10, 1, 1", "5, 5, 1", "1, 1, null"})
    @DisplayName("해당 프레임이 완료됐을 경우, 참을 반환한다.")
    public void isFinished(String firstPinCount, String secondPinCount, String thirdPinCount) throws Exception {
        Frame firstThrown = new FinalFrame().throwBowl(firstPinCount);
        Frame lastThrown = firstThrown.throwBowl(secondPinCount);

        if (!thirdPinCount.equals("null")) {
            lastThrown = lastThrown.throwBowl(thirdPinCount);
        }

        assertThat(lastThrown.isFinished()).isTrue();
    }

    @Test
    @DisplayName("해당 프레임이 완료되지 않았을 경우, 카운트 할 수 없는 점수를 반환한다.")
    public void score_unCountableScore() throws Exception {
        Frame secondAvailable = new FinalFrame().throwBowl("1");
        Frame thirdAvailableBySpare = new FinalFrame().throwBowl("1").throwBowl("9");
        Frame thirdAvailableByStrike = new FinalFrame().throwBowl("10").throwBowl("2");

        assertThat(secondAvailable.score()).isEqualTo(Score.unCountableScore());
        assertThat(thirdAvailableBySpare.score()).isEqualTo(Score.unCountableScore());
        assertThat(thirdAvailableByStrike.score()).isEqualTo(Score.unCountableScore());
    }

    @Test
    @DisplayName("해당 프레임이 완료됐을 경우, 최종 점수를 반환한다.")
    public void score_Miss() throws Exception {
        Frame secondFinished = new FinalFrame().throwBowl("1").throwBowl("2");
        Frame thirdFinishedWithSpare = new FinalFrame().throwBowl("1").throwBowl("9").throwBowl("1");
        Frame thirdFinishedWithStrike = new FinalFrame().throwBowl("10").throwBowl("2").throwBowl("8");

        assertThat(secondFinished.score()).isEqualTo(Score.Miss(3));
        assertThat(thirdFinishedWithSpare.score()).isEqualTo(Score.Miss(11));
        assertThat(thirdFinishedWithStrike.score()).isEqualTo(Score.Miss(20));
    }

    @Test
    @DisplayName("프레임이 한 번도 플레이 되지 않은 경우, 카운트 할 수 없는 점수를 반환한다.")
    public void add_with_empty_pinCount() throws Exception {
        Frame notThrownFrame = new FinalFrame();
        Score score = notThrownFrame.add(Score.Strike(10));
        assertThat(score).isEqualTo(Score.unCountableScore());
    }

    @ParameterizedTest
    @CsvSource(value = {"0", "9"})
    @DisplayName("점수를 2번 더할 수 있는 점수가 인자로 들어오지만, 최종 프레임이 한 번 이하로 플레이 되었을 경우, 카운트 할 수 없는 점수를 반환한다.")
    public void add_score_with_two_opportunities_fail(String firstPinCount) throws Exception {
        Frame firstThownFrame = new FinalFrame().throwBowl(firstPinCount);
        Score score = firstThownFrame.add(Score.Strike(10));
        assertThat(score).isEqualTo(Score.unCountableScore());
    }

    @ParameterizedTest
    @CsvSource(value = {"0", "9"})
    @DisplayName("점수를 1번 더할 수 있는 점수가 인자로 들어오고, 최종 프레임이 한 번 이상 플레이가 되었다면, 최종 점수(최종 프레임 초구 점수 + 인자로 받은 점수)를 반환한다.")
    public void add_score_with_one_opportunity(String firstPinCount) throws Exception {
        Frame frame = new FinalFrame().throwBowl(firstPinCount);
        Score score = frame.add(Score.Spare(10));
        assertThat(score).isEqualTo(Score.Miss(10 + Integer.parseInt(firstPinCount)));
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 8", "2, 7", "3, 6", "4, 5"})
    @DisplayName("점수를 2번 더할 수 있는 점수가 인자로 들어오고, 최종 프레임이 두 번 플레이가 되었다면, 최종 점수(최종 프레임 초구, 2구 점수 + 인자로 받은 점수)를 반환한다.")
    public void add_score_with_two_opportunities(String firstPinCount, String secondPinCount) throws Exception {
        Frame frame = new FinalFrame().throwBowl(firstPinCount).throwBowl(secondPinCount);
        Score score = frame.add(Score.Strike(10));
        assertThat(score).isEqualTo(Score.Miss(10 + Integer.parseInt(firstPinCount) + Integer.parseInt(secondPinCount)));
    }
}
