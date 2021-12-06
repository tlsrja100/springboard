package com.springboot.board.controller;

import com.springboot.board.Repository.BoardRepository;
import com.springboot.board.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardApiController {

    @Autowired
    private BoardRepository repository;

    @GetMapping("/boards")
    List<Board> all(@RequestParam(required = false, defaultValue = "") String title,
                    @RequestParam(required = false, defaultValue = "") String content) {
        if (StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
            return repository.findAll();
        } else {
            return repository.findByTitleOrContent(title,content);
        }
    }

    @PostMapping("/boards")
    Board newBoard(@RequestBody Board newBoard) {
        return repository.save(newBoard);
    }

    @GetMapping("/boards/{bno}")
    Board one(@PathVariable Long bno) {
        return repository.findById(bno).orElse(null);
    }

    @PutMapping("/boards/{bno}")
    Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long bno) {
        return repository.findById(bno)
                .map(Board -> {
                    Board.setTitle(newBoard.getTitle());
                    Board.setWriter(newBoard.getWriter());
                    Board.setContent(newBoard.getContent());
                    return repository.save(Board);
                })
                .orElseGet(() -> {
                    newBoard.setBno(bno);
                    return repository.save(newBoard);
                });
    }

    @DeleteMapping("/boards/{bno}")
    void deleteBoard(@PathVariable Long bno) {
        repository.deleteById(bno);
    }
}
