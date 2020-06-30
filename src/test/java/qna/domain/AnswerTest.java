package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변 작성자와 일치하는지 확인")
    @Test
    public void ownerTest() {
        // given
        Answer answer = A1;
        User user = UserTest.JAVAJIGI;

        // when
        boolean result = answer.isOwner(user);

        // then
        assertThat(result).isTrue();

    }

    @DisplayName("삭제가 잘 되는지 확인")
    @Test
    public void deleteTest() {
        // given
        Answer answer = A1;

        // when
        answer.delete();

        // then
        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("삭제 시 History가 잘 반환되는지 확인")
    @Test
    public void deleteHistorytest() {
        // given
        Answer answer = A1;

        // when
        DeleteHistory history = answer.delete();

        // then
        assertAll(
                () -> assertThat(answer.isDeleted()).isTrue(),
                () -> assertThat(history.isContentType(ContentType.ANSWER)).isTrue(),
                () -> assertThat(history.isOwner(UserTest.JAVAJIGI)).isTrue()
        );
    }
}
