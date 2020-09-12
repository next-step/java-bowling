package qna.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswerTest {
	public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
	public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

	@Test
	public void otherUserDeleteException() {
		assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
				.isInstanceOf(CannotDeleteException.class)
				.hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
	}

	@Test
	public void deleteSuccessTest() throws CannotDeleteException {
		A2.delete(UserTest.SANJIGI);
		assertThat(A2.isDeleted()).isEqualTo(Boolean.TRUE);
	}
}
