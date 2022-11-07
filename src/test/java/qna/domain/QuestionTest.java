package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {
	public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
	public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

	@Test
	void 로그인한_사용자가_질문자가_아닌경우_삭제_불가능() {
		assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
			.isInstanceOf(CannotDeleteException.class)
			.hasMessage("질문을 삭제할 권한이 없습니다.");
	}

	@Test
	void 질문_삭제() throws CannotDeleteException {
		assertThat(Q1.delete(UserTest.JAVAJIGI)).isEqualTo(List.of(
				new DeleteHistory(ContentType.QUESTION, Q1.getId(), UserTest.JAVAJIGI, null)
			)
		);
	}
}
