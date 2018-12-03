package domain.frame.state;

/**
 * Created by hspark on 22/11/2018.
 */

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * - STRIKE (한번에 다 쳤을경우)
 *  - STRIKE가 나왔을 경우, 다음 프레임 요청 시 다음 프레임을 생성해 돌려준다.
 *  - 마지막 프레임일 경우, Spare면 같은 프레임의 추가 프레임을 돌려준다.
 */
public class StrikeTest {

	@Test
	public void test_스트라이크_생성() {
		State strike = new Strike();
		Assertions.assertThat(strike.toString()).isEqualTo("X");
	}
}