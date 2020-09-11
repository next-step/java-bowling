package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {
	public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
	public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

	@Test
	public void otherUserDeleteException() {
		assertThatThrownBy(() -> Q2.delete(UserTest.JAVAJIGI))
				.isInstanceOf(CannotDeleteException.class)
				.hasMessage("질문을 삭제할 권한이 없습니다.");
	}

	@Test
	public void deleteSuccessTest() throws CannotDeleteException {
		Q2.delete(UserTest.SANJIGI);
		assertThat(Q2.isDeleted()).isEqualTo(Boolean.TRUE);
	}

	@Test
	public void addDeleteHistoryTest() throws CannotDeleteException {
		DeleteHistories deleteHistories = Q1.delete(UserTest.JAVAJIGI);
		assertThat(deleteHistories.getDeleteHistories()).isNotNull();
	}
}
