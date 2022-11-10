package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static qna.domain.AnswersTest.*;
import static qna.domain.UserTest.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import qna.CannotDeleteException;

class AnswerTest {
    @DisplayName("답변 소유자와 로그인 한 사용자가 동일하면 답변을 삭제할 수 있다.")
    @ParameterizedTest
    @ValueSource(longs = { 123, 45678, 9 })
    void delete(long contentId) throws CannotDeleteException {
        Answer answer = answer(contentId, JAVAJIGI, JAVAJIGI);
        assertThat(answer.delete(JAVAJIGI)).isEqualTo(new DeleteHistory(ContentType.ANSWER, contentId, JAVAJIGI, LocalDateTime.now()));
        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("답변 소유자와 로그인 한 사용자가 다르다면 답변을 삭제할 수 없다.")
    @Test
    void delete_when_invalid_user() {
        long contentId = 456L;
        Answer answer = answer(contentId, JAVAJIGI, JAVAJIGI);
        assertThatThrownBy(() -> answer.delete(SANJIGI)).isInstanceOf(CannotDeleteException.class)
                                                        .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        assertThat(answer.isDeleted()).isFalse();
    }
}
