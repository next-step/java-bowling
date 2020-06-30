package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FramesTest {

	@DisplayName("프레임의 일급 컬렉션은 프레임 목록을 10개 갖는다.")
	@Test
	void 프레임의_일급_콜렉션은_프레임개수가_10개이다() {
		Frames frames = Frames.of();
		List<Frame> frameList = frames.getFrameList();
		assertThat(frameList.size()).isEqualTo(10);
	}

	@DisplayName("마지막 프레임은 FinalFrame 인스턴스를 갖는다.")
	@Test
	void 마지막_프레임은_FinalFrame이다() {
		Frames frames = Frames.of();
		List<Frame> frameList = frames.getFrameList();
		assertThat(frameList.get(frameList.size() - 1)).isInstanceOf(FinalFrame.class);
	}

	@DisplayName("현재 게임이 진행 중인 프레임을 찾는다.")
	@Test
	void 게임_진행중인_프레임을_찾는다() {
		Frames frames = Frames.of();
		Frame playingFrame = frames.findPlayingFrame();
		assertThat(playingFrame).isNotNull();
	}
}
