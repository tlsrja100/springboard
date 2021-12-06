package com.springboot.board.validator;

import com.springboot.board.model.Board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class BoardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Board b = (Board) obj;
        if (StringUtils.isEmpty(b.getContent())) {
            errors.rejectValue("content", "key", "내용을 입력하세요");
        }
        if (StringUtils.isEmpty(b.getWriter())) {
            errors.rejectValue("writer", "key", "작성자를 입력하세요");
        }

    }
}
