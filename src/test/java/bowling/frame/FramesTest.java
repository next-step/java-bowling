package bowling.frame;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("프레임 컬렉션 테스트")
class FramesTest {

	private Frames frames;

	@BeforeEach
	void setUp() {
		frames = Frames.start();
	}

	static void strike(Frames frames) {
		frames.throwBowl(10);
	}

	static void spare(Frames frames) {
		frames.throwBowl(1);
		frames.throwBowl(9);
	}

	static void open(Frames frames) {
		frames.throwBowl(1);
		frames.throwBowl(1);
	}

	@Test
	void 특정_프레임_번호까지_점수_계산() {
		strike(frames);
		spare(frames);
		open(frames);

		assertAll(
			() -> assertThat(frames.sumUntil(1)).isEqualTo(20),
			() -> assertThat(frames.sumUntil(2)).isEqualTo(31),
			() -> assertThat(frames.sumUntil(3)).isEqualTo(33)
		);
	}

}