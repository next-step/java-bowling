package qna.domain.embeded;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import qna.domain.AnswerTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by seungwoo.song on 2022-11-07
 */
public class AnswersTest {

	public static final Answers A1 = new Answers(List.of(AnswerTest.A1, AnswerTest.A2));

	@ParameterizedTest
	@CsvSource(value = {"0", "1"})
	void 삭제(int index) {
		A1.deleteAll();
		assertThat(A1.get(index).isDeleted()).isTrue();
	}

	@Test
	void 모두삭제() {
		A1.deleteAll();
		assertThat(A1.isDeletedAll()).isTrue();
	}
}