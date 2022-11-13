package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @Test
    public void delete_성공() throws Exception {
        assertThat(Q1.isDeleted()).isFalse();
        Q1.questionDeleteHistories(UserTest.JAVAJIGI.getId());
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    public void delete_다른_사람이_쓴_글(){
        assertThatThrownBy(() -> {
            Q1.isOnwer(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_같음() throws Exception {
        Q2.isOnwer(UserTest.SANJIGI);
        assertThat(Q2.isDeleted()).isTrue();
    }
}
