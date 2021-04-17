package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static qna.domain.AnswerTest.*;
import static qna.domain.QuestionTest.Q1;

class AnswersTest {
    public static final Answers JAVAJIGI_ANSWERS = Answers.of(A1, A3);
    public static final Answers SHUFFLED_ANSWERS = Answers.of(A1, A2);

    @Test
    @DisplayName("모든 답변의 작성자가 로그인한 사람과 같다면 삭제한다.")
    void deleteAnswers() {
        assertThatCode(() -> JAVAJIGI_ANSWERS.beDeletedBy(UserTest.JAVAJIGI)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("일부 답변의 작성자가 로그인한 사람과 다르면 삭제할 수 없다.")
    void cannotDeleteAnswersIfOtherUserOwnSomeAnswers() {
        assertThatThrownBy(() -> SHUFFLED_ANSWERS.beDeletedBy(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제된 답변에서 삭제 히스토리를 생성한다.")
    void createDeleteHistoriesFromDeletedAnswers() {
        Answers deletedAnswers = JAVAJIGI_ANSWERS.beDeletedBy(UserTest.JAVAJIGI);

        List<DeleteHistory> expected = Arrays.asList(
            new DeleteHistory(ContentType.ANSWER, A1.getId(), Q1.getWriter(), LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, A3.getId(), Q1.getWriter(), LocalDateTime.now())
        );

        assertThat(deletedAnswers.createDeleteHistories()).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    @DisplayName("삭제되지 않은 답변에서 삭제 히스토리를 만들 수 없다.")
    void cannotCreateDeleteHistoriesFromActiveAnswers() {
        assertThatThrownBy(JAVAJIGI_ANSWERS::createDeleteHistories).isInstanceOf(RuntimeException.class);
    }
}
