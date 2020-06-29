package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswerTest {

	private Question question = new Question("How to learn java?", "I want to be a java master", UserTest.JAVAJIGI);
	private Answer A1 = new Answer(UserTest.JAVAJIGI, question, "Answers Contents1");
	private Answer A2 = new Answer(UserTest.JAVAJIGI, question, "Answers Contents2");
	private Answers answers;
	private User loginUser;
	private User guestUser;

	@BeforeEach
	void setUp() {
		answers = new Answers();
		answers.addAnswer(A1);
		answers.addAnswer(A2);
		loginUser = UserTest.JAVAJIGI;
		guestUser = User.GUEST_USER;
	}

	@DisplayName("답변을 삭제하고 해당 답변을 반환한다.")
	@Test
	void 답변을_삭제하고_이를_반환한다() throws CannotDeleteException {
		Answer deletedTheFirst = A1.delete();
		Answer deletedTheSecond = A2.delete();

		assertAll(
			() -> assertThat(deletedTheFirst).isEqualTo(A1),
			() -> assertThat(deletedTheSecond).isEqualTo(A2)
		);
	}

	@DisplayName("답변을 삭제하고 DeleteHistory 리스트를 반환한다.")
	@Test
	void 전체_답변을_삭제하고_삭제_리스트를_반환한다() throws CannotDeleteException {
		List<DeleteHistory> deleteHistories = answers.deleteAll(loginUser);
		assertAll(
			() -> assertThat(deleteHistories).isInstanceOf(List.class),
			() -> assertThat(deleteHistories.get(0)).isInstanceOf(DeleteHistory.class)
		);
	}

	@DisplayName("질문을 삭제할 때 답변자가 자기 자신이 아닌 경우 삭제할 수 없다.")
	@Test
	void 질문_삭제시_답변자가_자신이_아니면_삭제할_수_없다() {
		assertThatThrownBy(
			() -> answers.deleteAll(guestUser)
		).isInstanceOf(CannotDeleteException.class);
	}
}
