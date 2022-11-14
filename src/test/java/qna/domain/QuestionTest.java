package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("다른 사람이 쓴 글의 경우 CannotDeleteException을 throw한다.")
    public void validate_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> {
            Q1.validateQuestionAuthority(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("다른 사람이 쓴 답변이 있는 경우 CannotDeleteException을 throw한다.")
    public void validate_다른_사람의_답변_있는_글() {
        assertThatThrownBy(() -> {
            Q1.validateAnswerExists(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
