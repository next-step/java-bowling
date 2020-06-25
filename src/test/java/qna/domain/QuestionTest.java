package qna.domain;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(1, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question(2, "title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.")
    @Test
    void checkWriterTest() {
        assertThatThrownBy(() -> {
            Q1.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("1.질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.2.답변이 없는 경우 삭제가 가능하다.")
    @Test
    void deleteQuestionTest() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("질문자와 답변글의 모든 답변자 같은 경우 삭제가 가능하다.")
    @Test
    void checkAnswerWriterTest() throws CannotDeleteException {
        Answer answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Q1.addAnswer(answer);
        Q1.delete(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("질문자와 답변자가 다른 경우 답변을 삭제 할수없다.")
    @Test
    void checkAnswerOtherWriterTest() {
        Answer answer = new Answer(11L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1");
        Q1.addAnswer(answer);

        assertThatThrownBy(() -> {
            Q1.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경한다.")
    @Test
    void checkAnswerAndQuestionDeleteStatusTest() throws CannotDeleteException {
        Answer answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Q1.addAnswer(answer);
        Q1.delete(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.")
    @Test
    void deleteHistoryTest() throws CannotDeleteException {
        Answer answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Q1.addAnswer(answer);
        DeleteHistories deleteHistories = Q1.delete(UserTest.JAVAJIGI);

        assertThat(deleteHistories.getHistories().size()).isEqualTo(2);

    }
}