package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.global.exception.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private List<DeleteHistory> result;

    @Test
    @DisplayName("질문 삭제시 삭제 상태값 변경 확인")
    void deleteQuestionState() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문 삭제시 DeleteHistories 값 추가 확인")
    void addDeleteHistories() throws CannotDeleteException {
        result = Q1.delete(UserTest.JAVAJIGI);
        assertThat(result).hasSize(1);
    }

}
