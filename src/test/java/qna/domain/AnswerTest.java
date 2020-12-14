package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.QuestionTest.Q1;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, Q1, "Answers Contents2");

    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer(UserTest.JAVAJIGI, Q1,"");
    }

    @Test
    void owner() throws CannotDeleteException {
        assertThat(answer.isOwner(UserTest.JAVAJIGI)).isTrue();
    }

    @Test
    void isNotOwner() {
        assertThatThrownBy(() -> answer.isOwner(UserTest.SANJIGI))
                .withFailMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.")
                .isInstanceOf(CannotDeleteException.class);
    }
}
