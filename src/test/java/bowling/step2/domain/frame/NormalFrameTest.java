package bowling.step2.domain.frame;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

public class NormalFrameTest {

	@DisplayName(value = "첫번째 투구 시 스트라이크가 아닌 경우 같은 프레임 반환")
	@Test
	public void firstBowling() {
		NormalFrame frame = new NormalFrame(1);
		assertThat(frame.bowling(3)).isEqualTo(frame);
	}

	@DisplayName(value = "첫번째 투구 시 스트라이크인 경우 다음 프레임 생성")
	@Test
	public void createNextFrame() {
		Frame frame = new NormalFrame(1);
		Frame nextFrame = frame.bowling(10);
		assertThat(nextFrame).isNotEqualTo(frame);
		assertThat(nextFrame.getFrameNo()).isEqualTo(2);
	}

	@DisplayName(value = "두번째 투구 완료 후 다음 프레임 생성")
	@Test
	public void createNextFrame2() {
		Frame frame = new NormalFrame(1);
		frame = frame.bowling(3);
		Frame nextFrame = frame.bowling(4);

		assertThat(nextFrame).isNotEqualTo(frame);
		assertThat(nextFrame.getFrameNo()).isEqualTo(2);
	}

	@DisplayName(value = "첫번째 투구 시 볼링 핀 개수 유효성 확인")
	@ParameterizedTest
	@ValueSource(ints = {-5, 15})
	public void validPinCount(int pins) {
		Frame frame = new NormalFrame(1);
		assertThatIllegalArgumentException().isThrownBy(() -> frame.bowling(pins))
				.withMessage("쓰러트릴 수 있는 핀은 최소 0개, 최대 10개 입니다.");
	}

	@DisplayName(value = "두번째 투구 시 볼링 핀 개수 유효성 확인")
	@ParameterizedTest
	@CsvSource(value = {"2:10", "3:9", "9:4"}, delimiter = ':')
	public void validPinCount2(int firstPin, int secondPin) {
		Frame frame = new NormalFrame(1);
		frame.bowling(firstPin);
		assertThatIllegalArgumentException().isThrownBy(() -> frame.bowling(secondPin))
				.withMessage("쓰러트릴 수 있는 핀은 최대 10개 입니다.");
	}

	@DisplayName(value = "투구 시 스트라이크 아닌 경우 생성되는 점수판 결과")
	@Test
	public void makeFrameBoard() {
		Frame firstFrame = new NormalFrame(1);
		Frame currentFrame = firstFrame;
		for (int i = 0; i < 5; i++) {
			currentFrame = currentFrame.bowling(5);
		}

		FrameBoard board = firstFrame.makeBoard();
		assertThat(board.getResults()).hasSize(3);
		assertThat(board.getResults()).hasToString("[5|/, 5|/, 5]");
	}
}
