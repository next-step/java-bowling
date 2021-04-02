package bowling.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import bowling.exception.UserException;

public class WardTest {

	@ParameterizedTest
	@DisplayName("ward 정상 케이스 테스트")
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
	void ward_success(int input) {

		// when
		Ward ward = new Ward(input) {
			@Override
			boolean hasNext() {
				return false;
			}
		};

		// then
		assertThat(ward)
			.isInstanceOf(Ward.class);
	}

	@ParameterizedTest
	@DisplayName("ward exception 케이스 테스트")
	@ValueSource(ints = {11, 12, 13, 14, 15, 16, 17, 18, 19})
	void ward_exception(int input) {

		assertThatExceptionOfType(UserException.class)
			.isThrownBy(() -> {
				new Ward(input) {
					@Override
					boolean hasNext() {
						return false;
					}
				};
			});
	}
}
