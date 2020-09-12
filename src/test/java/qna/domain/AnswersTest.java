package qna.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswersTest {

	@Test
	public void otherUserDeleteException() {
		Answers answers = Answers.of(Arrays.asList(AnswerTest.A2));
		assertThatThrownBy(() -> answers.deleteAnswers(UserTest.JAVAJIGI))
				.isInstanceOf(CannotDeleteException.class)
				.hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
	}
}
