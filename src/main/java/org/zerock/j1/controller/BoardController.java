package org.zerock.j1.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.j1.dto.BoardDTO;
import org.zerock.j1.dto.BoardListRcntDTO;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/board")  // 이 컨트롤러의 기본 URL 경로를 "/api/board"로 지정합니다.
@RequiredArgsConstructor   // 필수 생성자를 자동으로 생성합니다.
@CrossOrigin   // Cross-Origin 요청 허용을 설정합니다.
@Log4j2   // Lombok을 사용하여 로깅을 위한 로그 변수를 생성합니다.
public class BoardController {

    private final BoardService boardService;  // BoardService를 주입 받습니다.

    @GetMapping(value = "/list") // HTTP GET 요청을 처리하는 메서드를 정의합니다. URL 경로는 "/api/board/list"입니다.
    public PageResponseDTO<BoardListRcntDTO> list(@ParameterObject PageRequestDTO requestDTO) {
        log.info(requestDTO); // 받아온 PageRequestDTO를 로깅합니다.
        return boardService.listRcnt(requestDTO);  // boardService를 통해 최근 게시글 목록을 가져와 반환합니다.
    }

    @GetMapping("/{bno}") // HTTP GET 요청을 처리하는 메서드를 정의합니다. URL 경로는 "/api/board/{bno}" 형태입니다.
    public BoardDTO get(@PathVariable("bno") Long bno) { // 경로 변수인 {bno}을 받아와서 해당 게시글의 상세 정보를 가져옵니다.
        return boardService.getOne(bno); // boardService를 통해 해당 게시글의 상세 정보를 반환합니다.
    }
}
