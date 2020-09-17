package qna.domain;

import org.junit.Before;
import org.junit.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    private List<DeleteHistory> deleteHistories = new ArrayList<>();

    private Question question;
    private Answer answer;

    @Before
    public void setUp() throws Exception {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }


    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        question.delete(deleteHistories, UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    public void delete_다른_사람이_쓴_글() throws Exception {
        assertThatThrownBy(() -> {
            question.delete(deleteHistories, UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
