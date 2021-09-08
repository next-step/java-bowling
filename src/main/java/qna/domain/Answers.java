package qna.domain;

import qna.ForbiddenException;

import java.util.List;

public class Answers {

    private List<Answer> answers;

    private boolean deleted = false;


    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    private Answers setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted(){
        return deleted;
    }

    public Answers delete(User loginUser) {

        if(answers.stream().filter(answer -> !answer.isOwner(loginUser)).findFirst().isPresent()){
            throw new ForbiddenException("본인이 작성하지 않은 답변이 있어서, 삭제할 수 없습니다");
        }

        return setDeleted(true);
    }
}
