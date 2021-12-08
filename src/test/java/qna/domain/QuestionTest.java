package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.UserTest.*;
import static qna.domain.UserTest.JAVAJIGI;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(SANJIGI);

    @DisplayName("다른 사람이 쓴 질문을 삭제하려면 예외를 던진다.")
    @Test
    void delete_byUnauthorized_throwsException() {
        assertThatThrownBy(() -> Q1.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("질문을 삭제하면 '질문, 답변'에 대한 삭제 히스토리를 반환한다.")
    @Test
    void delete_returnDeleteHistory() {
        //given
        Question question = question(1, JAVAJIGI);

        Answer answer1 = answer(1, JAVAJIGI, question);
        Answer answer2 = answer(2, JAVAJIGI, question);

        question.addAnswer(answer1);
        question.addAnswer(answer2);

        //when
        List<DeleteHistory> deleteHistories = question.delete(JAVAJIGI);

        //then
        assertThat(deleteHistories).containsExactlyInAnyOrder(
                new DeleteHistory(ContentType.QUESTION, 1L, JAVAJIGI),
                new DeleteHistory(ContentType.ANSWER, 1L, JAVAJIGI),
                new DeleteHistory(ContentType.ANSWER, 2L, JAVAJIGI)
        );
    }

    @DisplayName("삭제후 상태: deleted = true")
    @Test
    void delete_statusIsDeleted() {
        Question question = question(0, JAVAJIGI);
        question.delete(JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    private Question question(long id, User writer) {
        return new Question(id, "title1", "contents1").writeBy(writer);
    }

    private Answer answer(long id, User writer, Question question) {
        return new Answer(id, writer, question, "하이");
    }
}
