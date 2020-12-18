package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question actual;

    @BeforeEach
    void setUp() {
        this.actual = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("Question deleteAll() 성공 후 DeleteHistories 객체 비교")
    void question_deleteAllSuccess_isEqualTo() throws CannotDeleteException {
        DeleteHistories expect = DeleteHistories.of(Collections.singletonList(
                new DeleteHistory(ContentType.QUESTION, actual.getId(), actual.getWriter(), LocalDateTime.now()))
        );

        assertThat(actual.deleteAll(UserTest.JAVAJIGI)).isEqualTo(expect);
    }

    @Test
    @DisplayName("Question deleteAll() 다른 사람의 질문일 경우 CannotDeleteException 발생")
    void question_deleteAllFailed_throwException() {
        assertThatThrownBy(() -> actual.deleteAll(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }
}
