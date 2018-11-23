package bowlinggame.domain;

import org.junit.Test;

public class PlayerTest {

	@Test(expected = IllegalArgumentException.class)
	public void 사용자의_이름은_최대_3자() {
		new Player("hong");
	}
}