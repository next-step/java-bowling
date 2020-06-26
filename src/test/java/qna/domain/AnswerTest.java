package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("성공")
    @Test
    public void checkOwner() {
        assertThatCode(() -> A1.checkOwner(UserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThatCode(() -> A2.checkOwner(UserTest.SANJIGI)).doesNotThrowAnyException();
    }

    @DisplayName("실패")
    @Test
    public void checkOwnerFail() {
        assertThatThrownBy(() -> {
            A1.checkOwner(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> {
            A2.checkOwner(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);

    }

    @DisplayName("삭제")
    @Test
    public void delete() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        A1.delete(deleteHistories);
        assertThat(A1.isDeleted()).isTrue();
        A2.delete(deleteHistories);
        assertThat(A2.isDeleted()).isTrue();
        assertThat(deleteHistories.size()).isEqualTo(2);
    }

}
