package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변 삭제 테스트")
    @Test
    void delete_by_owner() {
        User owner = UserTest.JAVAJIGI;
        Answer answer = new Answer(1L, owner, QuestionTest.Q1, "Answers Contents1");
        LocalDateTime deleteDate = LocalDateTime.now();
        DeleteHistory expect = new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), deleteDate);
        DeleteHistory actual = answer.delete(owner, deleteDate);

        assertThat(answer.isDeleted()).isTrue();
        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("작성자가 아닌 사용자가 답변 삭제시 예외 발생 테스트")
    @Test
    void delete_by_not_owner() {
        User owner = UserTest.JAVAJIGI;
        User notOwner = UserTest.SANJIGI;
        Answer answer = new Answer(1L, owner, QuestionTest.Q1, "Answers Contents1");
        LocalDateTime deleteDate = LocalDateTime.now();

        assertThatThrownBy(() -> answer.delete(notOwner, deleteDate))
                .isInstanceOf(CannotDeleteException.class);

    }

}
