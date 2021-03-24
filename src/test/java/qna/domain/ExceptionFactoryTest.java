package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class ExceptionFactoryTest {

	@DisplayName("권한이 없는 사용자의 삭제 행위")
	@Test
	void NO_AUTHORIZED_USER() {
		assertThat(ExceptionFactory.NO_AUTHORIZED_USER.getException())
			.isInstanceOf(CannotDeleteException.class)
			.hasMessage("질문을 삭제할 권한이 없습니다.");
	}

	@DisplayName("다른사람의 답변 Exception")
	@Test
	void ANOTHER_PERSON_ANSWER_Exception() {
		assertThat(ExceptionFactory.ANOTHER_PERSON_ANSWER.getException())
			.isInstanceOf(CannotDeleteException.class)
			.hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
	}
}
