package bowling.model.frame;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FramesTest {

	@Test
	@DisplayName("전체 프레임을 생성한다.")
	public void createFrames() {
		Frames frames = new Frames(getFrames());

		assertThat(frames).isEqualTo(new Frames(getFrames()));
	}

	@Test
	@DisplayName("전체 프레임의 볼링에 대한 정보를 알 수 있다.")
	public void playBowling() {
		Frames frames = new Frames(getFrames());
		frames.playBowling(10);

		assertThat(frames.getPresentFrame()).isEqualTo(2);
		assertThat(frames.getFrames().get(0).isGameEnd()).isTrue();

		frames.playBowling(5);
		assertThat(frames.getPresentFrame()).isEqualTo(2);
		assertThat(frames.getFrames().get(1).isGameEnd()).isFalse();
	}

	@Test
	@DisplayName("전체 프레임의 게임 진행 유무를 알 수 있다.")
	public void isContinueGame() {
		Frames frames = new FakeFrames(getFrames());
		frames.playBowling(10);

		assertThat(frames.isContinueGame()).isFalse();

		frames.playBowling(10);
		assertThat(frames.isContinueGame()).isFalse();

		frames.playBowling(10);
		assertThat(frames.isContinueGame()).isTrue();
	}

	@Test
	@DisplayName("전체 프레임을 초기화 한다.")
	public void initCreate() {
		Frames frames = Frames.initCreateFrames();

		assertThat(frames.getFrames().size()).isEqualTo(10);
	}

	private List<Frame> getFrames() {
		List<Frame> frames = new ArrayList<>();
		frames.add(new NormalFrame(1));
		frames.add(new NormalFrame(2));
		frames.add(new NormalFrame(3));
		return frames;
	}

	static class FakeFrames extends Frames {

		public FakeFrames(List<Frame> frames) {
			super(frames);
		}

		private boolean findEndFrame() {
			return super.getFrames().stream()
				.allMatch(Frame::isGameEnd);
		}

		@Override
		public boolean isContinueGame() {
			return findEndFrame();
		}
	}
}