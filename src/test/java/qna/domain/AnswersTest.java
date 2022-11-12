package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

class AnswersTest {

    private Answers answers;

    @DisplayName("로그인 사용자와 답변한 사람이 다른 답변이 존재하는 경우, 해당 답변이후의 답변은 삭제 불가능 해야 한다")
    @Test
    void delete_differentUser() {
        final Answer answer1 = new Answer(99L, UserTest.SANJIGI, QuestionTest.Q1, "JPA Answer");
        final Answer answer2 = new Answer(100L, UserTest.JAVAJIGI, QuestionTest.Q1, "Other User JPA Answer");

        answers = new Answers(List.of(answer1, answer2));

        assertThat(answer1.isDeleted()).isFalse();
        assertThat(answer2.isDeleted()).isFalse();
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> answers.delete(UserTest.SANJIGI))
            .withMessageContaining("답변을 삭제할 권한이 없습니다");
        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isFalse();
    }

    @DisplayName("로그인 사용자와 모든 답변의 답변한 사람이 같은 경우, 모든 답변이 삭제 가능 해야 한다")
    @Test
    void delete_sameUser() throws CannotDeleteException {
        final Answer answer1 = new Answer(99L, UserTest.JAVAJIGI, QuestionTest.Q1, "JPA Answer");
        final Answer answer2 = new Answer(100L, UserTest.JAVAJIGI, QuestionTest.Q1, "Another JPA Answer");

        answers = new Answers(List.of(answer1, answer2));

        final List<DeleteHistory> deleteHistories = answers.delete(UserTest.JAVAJIGI);
        assertThat(deleteHistories).containsExactly(
            new DeleteHistory(ContentType.ANSWER, 99L, UserTest.JAVAJIGI, LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, 100L, UserTest.JAVAJIGI, LocalDateTime.now())
        );
    }

}