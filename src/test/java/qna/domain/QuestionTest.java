package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    public Question question1;
    public Question question2;
    private Answer answer;

    @BeforeEach
    public void setUp() throws Exception {
        question1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question1.addAnswer(answer);
    }

    @DisplayName("로그인 유저가 질문 삭제 가능한지 체크")
    @Test
    public void delete() throws Exception {
        //given
        Q1.deleteAll(UserTest.JAVAJIGI);
    }

    @DisplayName("로그인 유저가 질문 쓴사람 아니면 exception")
    @Test
    public void delete_fail() throws Exception {
        //given
        assertThatThrownBy(
                () -> question1.deleteAll(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("Question 삭제 처리")
    @Test
    public void deleteAll_success() throws Exception {
        //given
        List<DeleteHistory> compare = Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, question1.getId(), question1.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));

        //when
        List<DeleteHistory> history = question1.deleteAll(UserTest.JAVAJIGI);

        //then
        assertThat(history.size()).isEqualTo(compare.size());
    }
}
