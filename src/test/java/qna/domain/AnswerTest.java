package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    
    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    @DisplayName("작성자가 본인이면 답변을 지울 수 있다.")
    @Test
    public void delete_success_owner() {
        assertThat(answer.isDeleted()).isFalse();

        answer.delete(UserTest.JAVAJIGI);

        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("작성자가 아니면 답변을 지울 수 없다.")
    @Test
    public void delete_fail_no_owner() {
        assertThat(answer.isDeleted()).isFalse();

        assertThatThrownBy(() -> answer.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");

        assertThat(answer.isDeleted()).isFalse();
    }

}
