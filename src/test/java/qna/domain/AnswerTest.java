package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @Test
    public void delete_답변_중_다른_사람이_쓴_글(){
        assertThatThrownBy(() -> {
            A1.isOwner(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        A1.isOwner(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }
}
