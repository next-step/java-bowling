package qna.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.Fixture.*;

@DisplayName("답변 테스트")
public class AnswerTest {
    private Answer answer;

    @Before
    public void setUp() throws Exception {
        answer = new Answer(11L, JAVAJIGI, Q1, "Answers Contents1");
    }

    @DisplayName("작성자와 다르면 예외를 발생시킨다")
    @Test
    public void deleteException() {
        assertThatThrownBy(() -> answer.delete(SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("삭제후 삭제 플래그 값은 TRUE")
    @Test
    public void deleteFlag() {
        assertThat(answer.isDeleted()).isFalse();

        answer.delete(JAVAJIGI);

        assertThat(answer.isDeleted()).isTrue();
    }
}
