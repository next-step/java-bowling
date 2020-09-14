package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("Question 삭제하기")
    void deleteBy_writer() throws CannotDeleteException {
        // given
        Question question = new Question("question title", "contents").writeBy(UserTest.JAVAJIGI);
        Answer answer1 = new Answer(UserTest.JAVAJIGI, question, "answer1 by javajigi");
        Answer answer2 = new Answer(UserTest.JAVAJIGI, question, "answer2 by javajigi");
        question.addAnswer(answer1);
        question.addAnswer(answer2);

        List<DeleteHistory> expected = new ArrayList<>();
        expected.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
        expected.add(answer1.delete());
        expected.add(answer2.delete());

        // when
        List<DeleteHistory> result = question.deleteBy(UserTest.JAVAJIGI);

        // then
        assertThat(result).isEqualTo(expected);

    }

    @Test
    @DisplayName("Question 삭제하기 실패 : 로그인한 유저랑 질문 작성자가 다른 경우")
    void deleteBy_other_user() {
        // given
        Question question = new Question("question title", "contents").writeBy(UserTest.JAVAJIGI);
        Answer answer1 = new Answer(UserTest.JAVAJIGI, question, "answer1 by javajigi");
        Answer answer2 = new Answer(UserTest.JAVAJIGI, question, "answer2 by javajigi");
        question.addAnswer(answer1);
        question.addAnswer(answer2);

        // when
        assertThatThrownBy(() -> { question.deleteBy(UserTest.SANJIGI); })
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");

    }

}
