package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;
import static qna.domain.UserTest.JAVAJIGI;

import java.time.LocalDateTime;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

class AnswersTest {

    private Answers noOwnershipAnswers;

    @BeforeEach
    void setup() {
        noOwnershipAnswers = new Answers(Arrays.asList(A1, A2));
    }

    @DisplayName("Owner가 아닌 Answer이 있으면 CannotDeleteException")
    @Test
    void validateAnswersOwner() {
        User loginUser = JAVAJIGI;
        assertThatThrownBy(() -> noOwnershipAnswers.validateAnswersOwner(loginUser))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("Owner가 아닌 Answer이 있는 Answers를 삭제하려고 할 때 CannotDeleteException")
    @Test
    void deleteAnswersNoOwner() {
        User loginUser = JAVAJIGI;
        assertThatThrownBy(() -> noOwnershipAnswers.deleteAnswers(loginUser))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("Answer 삭제 테스트")
    @Test
    void deleteAnswers() throws CannotDeleteException {
        Answer answer1 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer answer2 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
        Answers ownershipAnswers = new Answers(Arrays.asList(answer1, answer2));
        User loginUser = JAVAJIGI;
        DeleteHistories deleteHistories = ownershipAnswers.deleteAnswers(loginUser);

        DeleteHistory deleteHistory1 = new DeleteHistory(ContentType.ANSWER, answer1.getId(), answer1.getWriter(), LocalDateTime.now());
        DeleteHistory deleteHistory2 = new DeleteHistory(ContentType.ANSWER, answer2.getId(), answer2.getWriter(), LocalDateTime.now());

        assertThat(deleteHistories).isEqualTo(new DeleteHistories(Arrays.asList(deleteHistory1, deleteHistory2)));
    }
}
