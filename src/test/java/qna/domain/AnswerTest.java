package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    Answer instanceA1;
    Answer instanceA2;

    @BeforeEach
    void setup() {
        instanceA1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        instanceA2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    }

    @DisplayName("질문자와 답변자가 일치하면 삭제 가능하다.")
    @Test
    void delete_질문자_답변자_일치() throws CannotDeleteException {
        instanceA1.deleteBy(UserTest.JAVAJIGI);
        assertThat(instanceA1.isDeleted()).isTrue();
    }

    @DisplayName("질문자와 답변자가 일치하지 않으면 삭제할 수 없다. (Exception 발생)")
    @Test
    void delete_notAnswerOwner() {
        assertThatThrownBy(() -> A1.deleteBy(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> A2.deleteBy(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
