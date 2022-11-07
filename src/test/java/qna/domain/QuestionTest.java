package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {

	public static final Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
	public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

	@Test
	@DisplayName("질문자와 로그인한 사람이 일치하지 않으면 예외 반환")
	void isOwnerException() {
		assertThatThrownBy(() -> Q1.isOwner(SANJIGI))
			.isInstanceOf(CannotDeleteException.class);

		assertThatThrownBy(() -> Q2.isOwner(JAVAJIGI))
			.isInstanceOf(CannotDeleteException.class);

	}

	@Test
	@DisplayName("질문자와 답변자가 일치하지 않으면 삭제 불가 반환")
	void isAnswerOwner() {
		Answer answer1 = new Answer(JAVAJIGI, Q1, "test1");
		Q1.addAnswer(answer1);
		assertThatThrownBy(() -> Q1.isAnswerOwner(SANJIGI))
			.isInstanceOf(CannotDeleteException.class);

	}

	@Test
	@DisplayName("논리 삭제 구현")
	void deleted() throws CannotDeleteException {
		Q1.delete(JAVAJIGI);
		assertThat(Q1.isDeleted()).isEqualTo(true);
	}

	@Test
	@DisplayName("Answers 논리 삭제 구현")
	void deletedAnswers() throws CannotDeleteException {
		Q1.deleteAnswer();
		assertThat(Q1.isAnswersDeleted()).isEqualTo(true);
	}
}
