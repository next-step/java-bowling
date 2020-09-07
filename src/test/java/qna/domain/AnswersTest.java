package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;
import static qna.domain.UserTest.JAVAJIGI;

class AnswersTest {

	private Answers nonOwnershipAnswers;

	@BeforeEach
	void setup() {
		nonOwnershipAnswers = new Answers(List.of(A1, A2));
	}

	@DisplayName("객체 생성 테스트")
	@Test
	void testConstruct() {
		Answers answers = new Answers();
		answers.add(new Answer());
		assertThat(answers).isEqualTo(new Answers(new Answer()));
	}

	@DisplayName("Owner가 아닌 Answer이 있으면 CannotDeleteException 던짐")
	@Test
	void throwExceptionIfContainsNoneOwnerAnswer() {
		User loginUser = JAVAJIGI;
		assertThatExceptionOfType(CannotDeleteException.class)
				.isThrownBy(() -> nonOwnershipAnswers.validateAnswersOwner(loginUser));
	}

	@DisplayName("Owner가 아닌 Answer이 있는 Answers를 삭제하려고 할 때 CannotDeleteException 던짐")
	@Test
	void throwExceptionIfTryDeletingAnswersContainsNoneOwnerAnswer() {
		User loginUser = JAVAJIGI;
		assertThatExceptionOfType(CannotDeleteException.class)
				.isThrownBy(() -> nonOwnershipAnswers.deleteAnswers(loginUser));
	}

	@DisplayName("삭제 할 수 있는 Answer들 인경우 Answer에 delete로 상태 변경 후 DeleteHistories 객체 반환")
	@Test
	void deleteAnswers() throws CannotDeleteException {
		Answer answer1 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
		Answer answer2 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
		Answers ownershipAnswers = new Answers(List.of(answer1, answer2));
		User loginUser = JAVAJIGI;
		DeleteHistories deleteHistories = ownershipAnswers.deleteAnswers(loginUser);
		DeleteHistory deleteHistory1 = new DeleteHistory(ContentType.ANSWER, answer1.getId(), answer1.getWriter(), LocalDateTime.now());
		DeleteHistory deleteHistory2 = new DeleteHistory(ContentType.ANSWER, answer2.getId(), answer2.getWriter(), LocalDateTime.now());
		assertThat(deleteHistories).isEqualTo(new DeleteHistories(List.of(deleteHistory1, deleteHistory2)));
	}

}
