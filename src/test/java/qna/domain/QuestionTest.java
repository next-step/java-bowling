package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {
	public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
	public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

	@Test
	@DisplayName("질문에 대한 삭제 권한을 체크한다.")
	public void checkAuthority() {
		assertThrows(CannotDeleteException.class, () ->
			Q1.deleteQuestion(UserTest.SANJIGI)
		);
	}

	@Test
	@DisplayName("질문을 삭제하면 질문의 상태값이 변경된다.")
	public void setDeleted() {
		Question result = Q1.setDeleted(true);

		assertThat(result.isDeleted()).isTrue();

		Question result2 = Q2.setDeleted(true);

		assertThat(result2.isDeleted()).isTrue();
	}

	@Test
	@DisplayName("질문을 삭제하면 질문의 히스토리가 저장된다.")
	public void deleted() {
		List<DeleteHistory> deleteHistories = new ArrayList<>();
		List<DeleteHistory> deleteHistory = Q1.deleted(deleteHistories);

		assertThat(deleteHistory).extracting("contentType").containsExactly(ContentType.QUESTION);
		assertThat(deleteHistory).extracting("contentId").containsExactly(Q1.getId());
		assertThat(deleteHistory).extracting("deletedBy").containsExactly(Q1.getWriter());
	}
}
