package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(SANJIGI);

    public Question preparedQuestion;

    @BeforeEach
    void setup() {
        preparedQuestion = new Question("title1", "contents1").writeBy(JAVAJIGI);
    }

    @DisplayName("질문 작성자가 아닐경우 CannotDeleteException 발생")
    @Test
    void throwExceptionIfNotQuestionOwner() {
        assertThatThrownBy(() -> preparedQuestion.validateQuestionOwner(SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("삭제 메서드 실행 시 question 삭제 플래그 true,  List<deleteHistory> 객체 반환")
    @Test
    void deleteQuestion() throws Exception {
        DeleteHistories deleteResult = preparedQuestion.delete(JAVAJIGI);
        assertThat(preparedQuestion.isDeleted()).isTrue();
        assertThat(deleteResult).isEqualTo(List.of(new DeleteHistory(ContentType.QUESTION, preparedQuestion.getId(), JAVAJIGI, LocalDateTime.now())));
    }
}
