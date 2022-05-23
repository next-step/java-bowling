package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.NotFoundDeleteHistoryException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {

    private Question question;
    private Question questionWithAnswer;

    @BeforeEach
    public void setUp() {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);

        Answer answer = new Answer(11L, UserTest.JAVAJIGI, question, "Answers Contents1");
        questionWithAnswer = new Question(1L, "title1", "contents1", new Answers(List.of(answer))).writeBy(UserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("다른 사람이 쓴 글일 경우 CannotDeleteException 반환한다.")
    public void invalidWriter() {
        assertThatThrownBy(() -> question.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문이 삭제되면 삭제 상태 값이 true 된다.")
    public void delete() throws Exception {
        assertThat(question.deleted()).isFalse();
        question.delete(UserTest.JAVAJIGI);
        assertThat(question.deleted()).isTrue();
    }

    @Test
    @DisplayName("삭제 이력을 조회했을때 삭제가 되지 않은 경우 NotFoundDeleteHistoryException 를 반환한다.")
    public void invalidDeleteHistory() {
        assertThatThrownBy(() -> question.deleteHistories()).isInstanceOf(NotFoundDeleteHistoryException.class);
    }

    @Test
    @DisplayName("답변이 없는 질문이 삭제되었을때 삭제이력은 질문만 포함한다.")
    public void deleteHistory() throws Exception {
        question.delete(UserTest.JAVAJIGI);
        assertThat(question.deleteHistories()).containsExactly(new DeleteHistory(ContentType.QUESTION, 1L, UserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @Test
    @DisplayName("답변이 있는 질문이 삭제되었을때 삭제이력은 질문과 답변 모두 포함한다.")
    public void deleteHistoryWithAnswer() throws Exception {
        questionWithAnswer.delete(UserTest.JAVAJIGI);
        assertThat(questionWithAnswer.deleteHistories()).containsExactly(new DeleteHistory(ContentType.QUESTION, 1L, UserTest.JAVAJIGI, LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, 11L, UserTest.JAVAJIGI, LocalDateTime.now()));
    }
}
