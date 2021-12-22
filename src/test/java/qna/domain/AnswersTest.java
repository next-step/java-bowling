package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    public static final Answers AS = new Answers(Collections.singletonList(AnswerTest.A1));
    public static final Answer A = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void create_답변들_생성() {
        assertThat(new Answers(Collections.singletonList(A)).getAnswers().get(0)).isEqualTo(A);
        assertThat(new Answers(Collections.singletonList(A)).getAnswers().size()).isEqualTo(1);
    }

    @Test
    void append_답변_추가() {
        assertThat(AS.append(A).getAnswers()).containsExactly(AnswerTest.A1, A);
    }

    @Test
    void delete_답변들_삭제_실패() {
        assertThatThrownBy(() -> AS.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void delete_답변들_삭제_성공() throws CannotDeleteException {
        // when
        AS.delete(UserTest.JAVAJIGI);
        // then
        assertThat(AS.isDeleted()).isTrue();
    }
}
