package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("다른 사람의 답변이 존재하는 경우 삭제할 수 없습니다.")
    void exitsAnswerUser() {
        assertThatThrownBy(() -> {
            Q1.addAnswer(AnswerTest.A1);
            List<Answer> answers = Q1.exitsAnswerUser(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("로그인한 사용자와 다른 경우 삭제가 불가합니다.")
    void validateIsOwner() {
        assertThatThrownBy(() -> {
            Q1.validateIsOwner(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
