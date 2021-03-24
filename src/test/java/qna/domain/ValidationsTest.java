package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static qna.domain.QuestionTest.*;
import static qna.domain.UserTest.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class ValidationsTest {

	private Question question;
	private Question sangjigi;
	private Answer answer;

	@BeforeEach
	public void setUp() throws Exception {
		sangjigi = new Question(1L, "title1", "contents1").writeBy(SANJIGI);
		question = new Question(1L, "title1", "contents1").writeBy(JAVAJIGI);
		answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
		question.addAnswer(answer);
		sangjigi.addAnswer(answer);
	}

	@Test
	@DisplayName("Validations test")
	public void validations_test() {
		Validations.validate(Q1, JAVAJIGI);
		Validations.validate(Q2, SANJIGI);
	}

	@Test
	@DisplayName("Validations test 실패 NO_AUTHORIZED_USER")
	public void validations_test_fail() {

		assertThatExceptionOfType(CannotDeleteException.class)
		.isThrownBy(() -> {
			Validations.validate(question, SANJIGI);
		})
			.withMessageMatching("질문을 삭제할 권한이 없습니다.");
	}

	@Test
	@DisplayName("Validations test 실패 NO_AUTHORIZED_USER")
	public void validations_test_fail2() {

		assertThatExceptionOfType(CannotDeleteException.class)
			.isThrownBy(() -> {
				Validations.validate(sangjigi, SANJIGI);
			})
			.withMessageMatching("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
	}
}
