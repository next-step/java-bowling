package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.NotFoundDeleteHistoryException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    private Answers answers;

    @BeforeEach
    public void setUp() {
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);

        Answer answer = new Answer(11L, UserTest.JAVAJIGI, question, "Answers Contents1");
        answers = new Answers(List.of(answer));
    }

    @Test
    @DisplayName("다른 사람이 쓴 글일 경우 CannotDeleteException 반환한다.")
    public void invalidWriter() {
        assertThatThrownBy(() -> answers.deleteAll(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class)
                .hasMessage("contentType=ANSWER, contentId=11, userId=sanjigi");
    }

    @Test
    @DisplayName("삭제 이력을 조회했을때 삭제가 되지 않은 경우 NotFoundDeleteHistoryException 를 반환한다.")
    public void invalidDeletedAnswerHistories() {
        assertThatThrownBy(() -> answers.deletedAnswerHistories()).isInstanceOf(NotFoundDeleteHistoryException.class);
    }
}