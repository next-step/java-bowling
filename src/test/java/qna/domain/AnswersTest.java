package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswersTest {

	@Test
	@DisplayName("정상 삭제 : 테스트 답변글도 모두 내 것")
	void validDeleteAllTest() {
		Answer answer1 = new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "contents1");
		Answer answer2 = new Answer(2L, UserTest.JAVAJIGI, QuestionTest.Q1, "contents2");
		Answers answers = new Answers(Arrays.asList(answer1, answer2));

		answers.deleteAll(UserTest.JAVAJIGI);
		answers.answers().forEach(answer -> {
			assertThat(answer.isDeleted()).isTrue();
		});
	}

	@Test
	@DisplayName("비정상 삭제 : 다른 사람 답글이 포함 돼 있는 경우")
	void invalidDeleteAllTest() {
		Answer answer1 = new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "contents1");
		Answer answer2 = new Answer(2L, UserTest.SANJIGI, QuestionTest.Q1, "contents2");
		Answers answers = new Answers(Arrays.asList(answer1, answer2));
		assertThatThrownBy(() -> answers.deleteAll(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
	}
}
