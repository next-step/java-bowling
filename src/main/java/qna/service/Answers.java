package qna.service;

import qna.domain.Answer;
import qna.domain.DeleteHistory;
import qna.domain.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 질문에 대한 답변들 일급 컬랙션
 */
public class Answers {

    private final Question question;
    private final List<Answer> answers;

    public Answers(Question question, List<Answer> answers) {
        if( answers == null ) answers = new ArrayList<>();
        this.question = question;
        this.answers = answers;
    }

    public boolean hasAnswerOfOthers() {
        for (Answer answer : answers) {
            if (!answer.isOwner(question.getWriter()))
                return true;
        }
        return false;
    }

    public List<DeleteHistory> delete() {
        return answers.stream()
                .map(answer -> answer.delete())
                .collect(Collectors.toList());
    }
}
