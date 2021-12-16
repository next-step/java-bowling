package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.UserTest.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(SANJIGI, QuestionTest.Q2, "Answers Contents2");
    public static final Answer A3 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents3");
    public static final Answer A4 = new Answer(SANJIGI, QuestionTest.Q2, "Answers Contents4");
    public static final Answer A5 = new Answer(JAVAJIGI, QuestionTest.Q2, "Answers Contents3");

    @Test
    @DisplayName("User 자신이 작성한 Answer 은 삭제가 가능하다.")
    void deleteBy_성공() throws CannotDeleteException {
        A1.deleteBy(JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("User 자신이 작성하지 않은 Answer 은 삭제가 불가하다.")
    void deleteBy_실패() {
        assertThatThrownBy(() -> {
            A2.deleteBy(JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);

        assertThatThrownBy(() -> {
            A1.deleteBy(SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
