package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NormalFrameTest {

	@Test
	void 일반_프레임을_생성한다(){
		NormalFrame normalFrame = new NormalFrame(0);
		assertThat(normalFrame).isEqualTo(new NormalFrame(0));
	}

	@Test
	void 인덱스가_맞지_않는_일반_프레임을_생성하면_오류가_발생한다(){
		assertThatThrownBy(() -> new NormalFrame(9))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void 첫번째_프레임이_생성된다(){
		NormalFrame normalFrame = NormalFrame.firstFrame();
		assertThat(normalFrame).isEqualTo(new NormalFrame(0));
	}

	@Test
	void 다음_프레임이_생성된다() {
		NormalFrame frame = NormalFrame.firstFrame();
		NormalFrame nextFrame = frame.next();
		assertThat(nextFrame).isEqualTo(new NormalFrame(1));
	}

	@Test
	void 핀을_맞춘다() {
		NormalFrame frame = NormalFrame.firstFrame();
		frame.pitch(10);
		assertThat(frame.getPins()).containsExactly(new Pin(10));
	}

	@Test
	void 하나의_프레임에_두번_공을_던진다() {
		NormalFrame frame = NormalFrame.firstFrame();
		frame.pitch(5);
		frame.pitch(5);
		assertThat(frame.getPins()).containsExactly(new Pin(5), new Pin(5));
	}

	@Test
	void 하나의_프레임에_세번_이상_공을_던질수_없다() {
		NormalFrame frame = NormalFrame.firstFrame();
		frame.pitch(1);
		frame.pitch(2);
		assertThatThrownBy(() -> frame.pitch(3))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void 하나의_프레임에_핀이_열개_이상이_넘어가면_추가로_플레이할수_없다() {
		NormalFrame frame = NormalFrame.firstFrame();
		frame.pitch(10);
		assertThatThrownBy(() -> frame.pitch(2))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
