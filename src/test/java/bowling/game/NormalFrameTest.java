package bowling.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NormalFrameTest {
    @DisplayName("첫 투구가 Strike가 아니면 그 프레임은 투구 기회가 있다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9})
    void firstBowlIsNotStrikeRemainChance(int firstPinCount) {
        Frame normalFrame = new NormalFrame(1);

        normalFrame.bowl(firstPinCount);

        assertThat(normalFrame.hasRemainChance()).isEqualTo(true);
    }

    @DisplayName("첫 투구가 Strike면 그 프레임은 더 이상 투구 기회가 없다.")
    @Test
    void firstBowlIsStrikeNotRemainChance() {
        Frame normalFrame = new NormalFrame(1);

        normalFrame.bowl(10);

        assertThat(normalFrame.hasRemainChance()).isEqualTo(false);
    }

    @DisplayName("2개의 투구를 모두 완료하면 그 프레임은 더 이상 투구 기회가 없다.")
    @ParameterizedTest
    @CsvSource({"9, 1", "8, 1", "0, 7", "0, 0"})
    void bowlTwiceNotRemainChance(int firstPinCount, int nextPinCount) {
        Frame normalFrame = new NormalFrame(1);

        normalFrame.bowl(firstPinCount);
        normalFrame.bowl(nextPinCount);

        assertThat(normalFrame.hasRemainChance()).isEqualTo(false);
    }

    @DisplayName("나보다 번호가 1 큰 프레임을 만든다.")
    @Test
    void createNextFrame() {
        int firstNumber = 1;
        Frame firstFrame = new NormalFrame(firstNumber);

        Frame nextFrame = firstFrame.createNextFrame();

        assertThat(nextFrame.getFrameNumber().getNumber()).isEqualTo(firstNumber + 1);
    }

    @DisplayName("점수 계산 시 아직 투구 기회가 남아있으면 빈 Optional을 반환한다.")
    @Test
    void createScore() {
        Frame frame = new NormalFrame(1);

        frame.bowl(3);

        assertThat(frame.calculateScore()).isEmpty();
    }

    @DisplayName("점수 계산 시 Miss 면 점수 계산 후 Optional을 반환한다.")
    @ParameterizedTest
    @CsvSource({"1, 3, 4", "2, 4, 6"})
    void calculateMissScore(int first, int second, int score) {
        Frame frame = new NormalFrame(1);

        frame.bowl(first);
        frame.bowl(second);

        Optional<Score> scoreOptional = frame.calculateScore();

        assertThat(scoreOptional).isNotEmpty();
        assertThat(scoreOptional.get().getScore()).isEqualTo(score);
    }

    @DisplayName("점수 계산 시 아직 보너수 점수 계산이 불가능 하면 빈 Optional을 반환한다.")
    @Test
    void canNotCalculateScore() {
        Frame frame = new NormalFrame(1);

        frame.bowl(10);

        Optional<Score> scoreOptional = frame.calculateScore();

        assertThat(scoreOptional).isEmpty();
    }

    @DisplayName("Strike는 다음 2번의 투구가 끝나면 보너스 점수 계산을 한다.")
    @Test
    void calculateStrikeScore() {
        Frame firstFrame = new NormalFrame(1);
        Frame secondFrame = firstFrame.createNextFrame();

        firstFrame.bowl(10);
        firstFrame.calculateScore();
        secondFrame.bowl(3);
        firstFrame.calculateScore();
        secondFrame.bowl(7);
        firstFrame.calculateScore();

        assertThat(firstFrame.calculateScore()).isNotEmpty();
        assertThat(firstFrame.calculateScore().get().getScore()).isEqualTo(20);
    }

    @DisplayName("Spare는 다음 1번의 투구가 끝나면 보너스 점수 계산을 한다.")
    @Test
    void calculateSpareScore() {
        Frame firstFrame = new NormalFrame(1);
        Frame secondFrame = firstFrame.createNextFrame();

        firstFrame.bowl(1);
        firstFrame.bowl(9);

        assertThat(firstFrame.calculateScore()).isEmpty();

        secondFrame.bowl(10);
        assertThat(firstFrame.calculateScore()).isNotEmpty();
        assertThat(firstFrame.calculateScore().get().getScore()).isEqualTo(20);
    }
}