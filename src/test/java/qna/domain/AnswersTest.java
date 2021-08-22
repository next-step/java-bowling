package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static qna.domain.AnswerTest.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

class AnswersTest {

	@Test
	@DisplayName("다른사람의 답변이 있으면 삭제가 불가능 하다.")
	public void checkAuthority() {
		List<Answer> answers = new ArrayList<>();
		answers.add(A1);
		answers.add(A2);
		assertThrows(CannotDeleteException.class, () ->
			new Answers(answers,UserTest.SANJIGI)
		);
	}

	@Test
	@DisplayName("답변들을 삭제시 히스토리가 저장된다.")
	public void saveDeleteHistory() throws CannotDeleteException {
		Answers answers = new Answers(Collections.singletonList(A1), UserTest.JAVAJIGI);
		List<DeleteHistory> deleteHistories = answers.deleteAnswer(new ArrayList<>());

		assertThat(deleteHistories).extracting("contentType").containsExactly(ContentType.ANSWER);
		assertThat(deleteHistories).extracting("contentId").containsExactly(A1.getId());
		assertThat(deleteHistories).extracting("deletedBy").containsExactly(A1.getWriter());
	}

}