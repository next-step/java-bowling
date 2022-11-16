package qna.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @AfterEach
    void tearDown() {
        A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    }

    @Test
    void validation_작성자_본인_여부_정상() {
        assertThatCode(() -> A1.validateOwner(UserTest.JAVAJIGI))
                .doesNotThrowAnyException();
    }

    @Test
    void validation_작성자_본인_여부_오류() {
        assertThatThrownBy(() -> A1.validateOwner(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 댓글_삭제() {
        DeleteHistories deleteHistories = A1.delete(new DeleteHistories(), LocalDateTime.now());

        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleteHistories.histories()).hasSize(1);
    }
}
