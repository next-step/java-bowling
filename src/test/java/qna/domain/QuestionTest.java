package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question(100L, "JPA", "JPA Content").writeBy(UserTest.JAVAJIGI);
    }

    @DisplayName("로그인 사용자와 질문한 사람이 다른 경우, 질문 삭제 불가능 해야 한다")
    @Test
    void delete_differentUser() {
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> question.delete(UserTest.SANJIGI))
            .withMessageContaining("질문을 삭제할 권한이 없습니다");
    }

    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우, 질문 삭제 가능 해야 한다")
    @Test
    void delete_sameUser() throws CannotDeleteException {
        assertThat(question.isDeleted()).isFalse();

        final List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
        assertThat(deleteHistories).containsExactly(
            new DeleteHistory(ContentType.QUESTION, 100L, UserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @DisplayName("다른 사람이 쓴 답변이 존재하는 경우, 질문 삭제 불가능 해야 한다")
    @Test
    void delete_containsOtherUserAnswers() {
        question.addAnswer(new Answer(1L, UserTest.SANJIGI, question,"It's me again, but.."));

        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> question.delete(UserTest.JAVAJIGI))
            .withMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다");
    }

    @DisplayName("본인 외에 다른 사람이 쓴 답변이 존재하지 않는 경우, 질문과 답변 모두 삭제 가능 해야 한다")
    @Test
    void delete_onlyContainsOwnAnswers() throws CannotDeleteException {
        question.addAnswer(new Answer(1L, UserTest.JAVAJIGI, question,"It's me again, but.."));

        final List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
        assertThat(deleteHistories).containsExactly(
            new DeleteHistory(ContentType.QUESTION, 100L, UserTest.JAVAJIGI, LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, 1L, UserTest.JAVAJIGI, LocalDateTime.now()));
    }


}
