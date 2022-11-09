package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private List<DeleteHistory> deleteHistories;

    @BeforeEach
    void setup() {
        deleteHistories = new ArrayList<>();
    }

    @DisplayName("로그인 사용자와 답변한 사람이 같은 경우 삭제 가능하다.")
    @Test
    void deleteByLoginUser() {
        assertThat(A1.delete(JAVAJIGI, deleteHistories)).hasSize(1);
        assertThat(A1.isDeleted()).isTrue();
    }

    @DisplayName("로그인 사용자와 답변한 사람이 다른 경우 삭제할 때 예외가 발생한다.")
    @Test
    void deleteByAnotherUser() {
        assertThatThrownBy(() -> {
            A1.delete(SANJIGI, deleteHistories);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
