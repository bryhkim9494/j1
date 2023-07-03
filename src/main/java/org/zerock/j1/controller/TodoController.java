package org.zerock.j1.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.TodoDTO;
import org.zerock.j1.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class TodoController {
    // CORS -> AJAX통신할때 발생할수있는 문제
    // CORS 는 중요함
    private final TodoService todoService;

    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> list() {
        return todoService.getList();
    }

    @PostMapping("/") // post방식은 브라우저로 확인안되니 postman으로 확인함
    public TodoDTO register(@RequestBody TodoDTO todoDTO) { // json으로 들어온 데이터를 변환해줌
        log.info("register ............................................................");
        log.info(todoDTO);
        return todoService.register(todoDTO);

    }

}
