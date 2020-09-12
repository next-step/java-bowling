package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.exception.PlayerNameException;
import bowling.user.Player;

public class PlayerTest {

	@ParameterizedTest
	@MethodSource("playerNameEmptyProvider")
	public void playerNameEmptyExceptionTest(String name) {
		assertThatThrownBy(() -> Player.of(name))
				.isInstanceOf(PlayerNameException.class)
				.hasMessage("플레이어 이름은 필수입니다.");
	}

	@ParameterizedTest
	@MethodSource("playerNameLengthNot3Provider")
	public void playerNameLengthNot3ExceptionTest(String name) {
		assertThatThrownBy(() -> Player.of(name))
				.isInstanceOf(PlayerNameException.class)
				.hasMessage("플레이어 이름은 3글자로 해주세요.");
	}

	@ParameterizedTest
	@MethodSource("playerNameProvider")
	public void playerNameTest(String name) {
		assertThat(Player.of(name)).isNotNull();
	}

	private static Stream<Arguments> playerNameEmptyProvider() {
		return Stream.of(
				Arguments.arguments(Strings.EMPTY),
				Arguments.arguments(" ")
						);
	}

	private static Stream<Arguments> playerNameLengthNot3Provider() {
		return Stream.of(
				Arguments.arguments("Toby"),
				Arguments.arguments("Mi"),
				Arguments.arguments("G"),
				Arguments.arguments("Michael"),
				Arguments.arguments("John")
						);
	}

	private static Stream<Arguments> playerNameProvider() {
		return Stream.of(
				Arguments.arguments("Tom"),
				Arguments.arguments("Joe"),
				Arguments.arguments("Kim")
						);
	}
}
