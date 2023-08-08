package org.zerock.j1.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.j1.domain.Board;
import org.zerock.j1.dto.BoardListRcntDTO;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;

public interface BoardSearch {   // 게시글 검색을 위한 인터페이스입니다.

    // 버전1: 검색 조건과 페이지 정보를 받아 게시글을 검색하는 메서드입니다.
    Page<Board> search1(String searchType, String keyword, Pageable pageable);   // 1. 인터페이스 메소드추가


    // 버전2: 검색 조건과 페이지 정보를 받아 게시글과 댓글 수를 함께 검색하는 메서드입니다.
    Page<Object[]> searchWithRcnt(String searchType, String keyword, Pageable pageable);

    // 버전3: PageRequestDTO를 이용하여 게시글 및 댓글 수 정보를 검색하여 반환하는 메서드입니다.
    PageResponseDTO<BoardListRcntDTO> searchDTORcnt(PageRequestDTO pageRequestDTO);

    default Pageable makePageable(PageRequestDTO requestDTO) {
        Pageable pageable = PageRequest.of(requestDTO.getPage() - 1, requestDTO.getSize(),   // 페이지 정보를 생성하는 디폴트 메서드입니다.
                Sort.by("bno").descending());
        return pageable;
    }

}