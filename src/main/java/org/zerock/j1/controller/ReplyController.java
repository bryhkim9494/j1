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
import org.zerock.j1.dto.ReplyDTO;
import org.zerock.j1.dto.ReplyPageRequestDTO;
import org.zerock.j1.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/replies")   // 이 컨트롤러의 기본 URL 경로를 "/api/replies"로 지정합니다.
@RequiredArgsConstructor   // 필수 생성자를 자동으로 생성합니다.
@CrossOrigin   // Cross-Origin 요청 허용을 설정합니다.
@Log4j2   // Lombok을 사용하여 로깅을 위한 로그 변수를 생성합니다.
public class ReplyController {

    private final ReplyService replyService;   // ReplyService를 주입 받습니다.

    @GetMapping("/{bno}/list")   // HTTP GET 요청을 처리하는 메서드를 정의합니다. URL 경로는 "/api/replies/{bno}/list"입니다.
    public PageResponseDTO<ReplyDTO> list(@PathVariable("bno") Long bno, ReplyPageRequestDTO requestDTO) {

        log.info("bno ---- " + bno);   // 경로 변수인 {bno} 값을 로깅합니다.
        log.info(requestDTO);   // 받아온 ReplyPageRequestDTO를 로깅합니다.
        return replyService.list(requestDTO);   // replyService를 통해 특정 게시글의 댓글 목록을 가져와 반환합니다.
    }

    @PostMapping("/")   // HTTP POST 요청을 처리하는 메서드를 정의합니다. URL 경로는 "/api/replies/"입니다.
    public Map<String, Long> register(@RequestBody ReplyDTO replyDTO) {

        log.info("ReplyController....................");   // 메서드 실행 로그를 남깁니다.
        log.info(replyDTO);   // 받아온 ReplyDTO를 로깅합니다.
        Long newRno = replyService.register(replyDTO);   // replyService를 통해 댓글을 등록하고, 생성된 댓글 번호(newRno)를 받아옵니다.
        return Map.of("result", newRno);   // 등록된 댓글 번호를 Map 형태로 반환합니다.
    }

    @GetMapping(value = "/{rno}")   // HTTP GET 요청을 처리하는 메서드를 정의합니다. URL 경로는 "/api/replies/{rno}"입니다.
    public ReplyDTO get(@PathVariable("rno") Long rno) {
        return replyService.read(rno);   // replyService를 통해 특정 댓글의 내용을 가져와 반환합니다.
    }

    @DeleteMapping("/{rno}")   // HTTP DELETE 요청을 처리하는 메서드를 정의합니다. URL 경로는 "/api/replies/{rno}"입니다.
    public Map<String, Long> remove(@PathVariable("rno") Long rno) {

        replyService.remove(rno);   // replyService를 통해 특정 댓글을 삭제합니다.
        return Map.of("result", rno);   // 삭제된 댓글 번호를 Map 형태로 반환합니다.
    }

    @PutMapping("/{rno}")   // HTTP PUT 요청을 처리하는 메서드를 정의합니다. URL 경로는 "/api/replies/{rno}"입니다.
    public Map<String, Long> modify(@RequestBody ReplyDTO replyDTO) {

        replyService.modify(replyDTO);   // replyService를 통해 특정 댓글을 수정합니다.
        return Map.of("result", replyDTO.getRno());   // 수정된 댓글 번호를 Map 형태로 반환합니다.
    }
}
