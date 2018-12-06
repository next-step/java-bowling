package bowlinggame.domain.frame.state;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ReadyTest {

	private State ready;

	@Before
	public void setUp() {
		ready = new Ready();
	}

	@Test
	public void 스트라이크() {
		assertThat(ready.roll(10)).isInstanceOf(Strike.class);
	}

	@Test
	public void 스트라이크가_아닐때() {
		assertThat(ready.roll(5)).isInstanceOf(FirstState.class);
	}
}