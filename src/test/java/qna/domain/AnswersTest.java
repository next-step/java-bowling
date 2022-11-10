package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AnswersTest {

    @Test
    public void delete_성공_질문자와답변글의모든답변자같은경우() throws Exception {
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        List<Answer> answerList = new ArrayList<>();
        answerList.add(answer);

        Answers answers = new Answers(answerList);

        List<DeleteHistory> deleteHistories = answers.delete(UserTest.JAVAJIGI);

        assertThat(answer.isDeleted()).isTrue();
        assertThat(deleteHistories.size()).isEqualTo(1);
    }

    @Test
    public void delete_실패_다른사람이쓴답변이있어삭제할수없습니다() throws Exception {
        Answer answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer answer2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
        Answers answers = new Answers(Arrays.asList(answer1, answer2));
        assertThatThrownBy(()-> {
            answers.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
