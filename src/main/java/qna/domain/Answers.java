package qna.domain;

import qna.domain.entity.Answer;
import qna.domain.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static qna.funtional.FunctionWithException.wrapper;

public class Answers {
    private List<Answer> answers;

    public Answers(){
         answers = new ArrayList<>();
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> delete(User loginUser){
        return answers.stream()
                .map(wrapper(answer -> answer.delete(loginUser)))
                .collect(Collectors.toList());
    }
}
