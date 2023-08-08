package org.zerock.j1.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.ReplyDTO;
import org.zerock.j1.dto.ReplyPageRequestDTO;

// 트랜잭션 처리를 위한 어노테이션인 Transactional을 가지고 있는 ReplyService 인터페이스 정의
@Transactional
public interface ReplyService {

    // 댓글 목록을 조회하여 PageResponseDTO로 반환하는 메소드
    // 리턴 타입은 ReplyDTO의 리스트를 갖는 PageResponseDTO입니다.
    PageResponseDTO<ReplyDTO> list(ReplyPageRequestDTO requestDTO); // 리턴타입이 ReplyDTO임

    // 댓글을 등록하고, 등록한 댓글의 rno를 Long 형태로 반환하는 메소드
    Long register(ReplyDTO replyDTO);

    // 특정 댓글의 상세 정보를 조회하여 ReplyDTO로 반환하는 메소드
    ReplyDTO read(Long rno);

    // 특정 댓글을 삭제하는 메소드
    void remove(Long rno);

    // 댓글 내용을 수정하는 메소드
    void modify(ReplyDTO replyDTO);

}
