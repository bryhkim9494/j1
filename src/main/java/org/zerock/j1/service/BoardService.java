package org.zerock.j1.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.j1.dto.BoardDTO;
import org.zerock.j1.dto.BoardListRcntDTO;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;

// 트랜잭션 처리를 위한 어노테이션인 Transactional을 가지고 있는 BoardService 인터페이스 정의
@Transactional
public interface BoardService {

    // 최근 댓글 수 정보를 포함한 게시물 목록을 조회하여 PageResponseDTO로 반환하는 메소드
    PageResponseDTO<BoardListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO);

    // 특정 게시물의 상세 정보를 조회하여 BoardDTO로 반환하는 메소드
    BoardDTO getOne(Long bno);
}
