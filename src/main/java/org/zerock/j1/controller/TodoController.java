package org.zerock.j1.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.TodoDTO;
import org.zerock.j1.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/todos")   // 이 컨트롤러의 기본 URL 경로를 "/api/todos"로 지정합니다.
@RequiredArgsConstructor   // 필수 생성자를 자동으로 생성합니다.
@CrossOrigin   // Cross-Origin 요청 허용을 설정합니다.
@Log4j2   // Lombok을 사용하여 로깅을 위한 로그 변수를 생성합니다.
public class TodoController {

    // CORS -> AJAX통신할때 발생할수있는 문제
    // CORS는 면접에서 물어볼정도로 중요함

    private final TodoService todoService;   // TodoService를 주입 받습니다.

    @GetMapping("/list")   // HTTP GET 요청을 처리하는 메서드를 정의합니다. URL 경로는 "/api/todos/list"입니다.
    public PageResponseDTO<TodoDTO> list() {

        return todoService.getList();   // todoService를 통해 할 일 목록을 가져와 반환합니다.
    }

    @GetMapping("/{tno}")   // HTTP GET 요청을 처리하는 메서드를 정의합니다. URL 경로는 "/api/todos/{tno}"입니다.
    public TodoDTO get(@PathVariable Long tno) {

        return todoService.getOne(tno);   // todoService를 통해 특정 할 일의 내용을 가져와 반환합니다.
    }

    @PostMapping("/")   // HTTP POST 요청을 처리하는 메서드를 정의합니다. URL 경로는 "/api/todos/"입니다.
    public TodoDTO register(@RequestBody TodoDTO todoDTO) {   // JSON 데이터를 받아와서 TodoDTO로 변환합니다.

        log.info("register ............................................................");
        log.info(todoDTO);   // 받아온 TodoDTO를 로깅합니다.
        return todoService.register(todoDTO);   // todoService를 통해 새로운 할 일을 등록하고 반환합니다.

    }

    @DeleteMapping("/{tno}")   // HTTP DELETE 요청을 처리하는 메서드를 정의합니다. URL 경로는 "/api/todos/{tno}"입니다.
    public Map<String, String> delete(@PathVariable("tno") Long tno) {

        todoService.remove(tno);   // todoService를 통해 특정 할 일을 삭제합니다.
        return Map.of("result", "success");   // 삭제 결과를 Map 형태로 반환합니다.
    }

    @PutMapping("/{tno}")   // HTTP PUT 요청을 처리하는 메서드를 정의합니다. URL 경로는 "/api/todos/{tno}"입니다.
    public Map<String, String> update(@PathVariable("tno") Long tno, @RequestBody TodoDTO todoDTO) {

        todoDTO.setTno(tno);   // 할 일 번호를 안전하게 설정합니다.
        todoService.modify(todoDTO);   // todoService를 통해 특정 할 일을 수정합니다.
        return Map.of("result", "success");   // 수정 결과를 Map 형태로 반환합니다.
    }


}
