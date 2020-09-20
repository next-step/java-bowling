package bowling.domain;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DownedPinCountTest {

	@DisplayName("int 값에 맞는 DownedPinCount를 얻을 수 있는지 테스트")
	@Test
	void from() {
		assertThat(DownedPinCount.fromDownCount(0)).isEqualTo(DownedPinCount.fromDownCount(0));
		assertThat(DownedPinCount.fromDownCount(1)).isEqualTo(DownedPinCount.fromDownCount(1));
		assertThat(DownedPinCount.fromDownCount(10)).isEqualTo(DownedPinCount.fromDownCount(10));
	}

	@DisplayName("0에서 10 사이의 값이 아닌 int value를 입력하면 Exception이 발생하는지 테스트")
	@Test
	void failFrom() {
		int given = 11;
		assertThatThrownBy(() -> DownedPinCount.fromDownCount(given))
				.isInstanceOf(BowlingException.class)
				.hasMessage(String.format("쓰러진 핀 수는 0 이상 10 이하의 자연수입니다. 입력 된 수 : %d",given));
	}

	@Test
	void test() {

		Temp temp1 = new Temp(2, 1);
		Temp temp2 = new Temp(1, 2);
		Temp temp3 = new Temp(0, 3);

		List<Temp> list = List.of(temp1, temp2, temp3);

		System.out.println(list.stream().map(Temp::getI).map(String::valueOf).collect(Collectors.joining(",")));

		list.stream().filter(Temp::isCaculatable).forEach(temp -> temp.add(1));

		System.out.println(list.stream().map(Temp::getI).map(String::valueOf).collect(Collectors.joining(",")));

		list.stream().filter(Temp::isCaculatable).forEach(temp -> temp.add(1));

		System.out.println(list.stream().map(Temp::getI).map(String::valueOf).collect(Collectors.joining(",")));

		list.stream().filter(Temp::isCaculatable).forEach(temp -> temp.add(1));

		System.out.println(list.stream().map(Temp::getI).map(String::valueOf).collect(Collectors.joining(",")));



	}


	private static class Temp {
		private int count;
		private int i;

		public Temp(int count, int i) {
			this.count = count;
			this.i = i;
		}

		public int getI() {
			return i;
		}

		public boolean isCaculatable() {
			return count > 0;
		}

		public void add(int num) {
			i = i + num;
			count--;
		}
	}


}
