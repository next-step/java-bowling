package bowlinggame.domain.frame.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.frame.Pin;
import org.junit.Before;
import org.junit.Test;

public class FirstStateTest {

	private FirstState firstState;

	@Before
	public void setUp() {
		firstState = new FirstState(Pin.of(5));
	}

	@Test
	public void 스페어() {
		assertThat(firstState.roll(5)).isInstanceOf(Spare.class);
	}

	@Test
	public void 미스() {
		assertThat(firstState.roll(3)).isInstanceOf(Miss.class);
	}
}