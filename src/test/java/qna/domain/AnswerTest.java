package qna.domain;

import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void deleteTest() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        A1.delete(UserTest.JAVAJIGI, deleteHistories);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    void delete_다른_사람의_답변_존재() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        assertThatThrownBy(
                () -> A1.delete(UserTest.SANJIGI, deleteHistories)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
