package org.zerock.j1.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.j1.domain.Board;
import org.zerock.j1.dto.BoardDTO;
import org.zerock.j1.dto.BoardListRcntDTO;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

// 서비스 계층을 나타내는 Service 어노테이션 및 필수적인 의존성 주입을 위한 RequiredArgsConstructor 어노테이션
@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServcieImpl implements BoardService {

    // BoardRepository와 ModelMapper를 의존성 주입 받음
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    // 게시물 목록을 조회하여 최근 댓글 수 정보와 함께 PageResponseDTO로 반환하는 메소드
    @Override
    public PageResponseDTO<BoardListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO) {
        log.info("---------------------------------------------------------------------------");
        log.info(pageRequestDTO);

        // BoardRepository의 searchDTORcnt 메소드를 활용하여 데이터 조회 후 반환
        return boardRepository.searchDTORcnt(pageRequestDTO);

    }

    // 특정 게시물의 상세 정보를 조회하여 BoardDTO로 반환하는 메소드
    @Override
    public BoardDTO getOne(Long bno) {
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow(); // Optional에서 데이터를 추출하여 Board 객체로 변환
        BoardDTO dto = modelMapper.map(board, BoardDTO.class);; // ModelMapper를 사용하여 Board 엔티티를 BoardDTO로 변환
        return dto;
    }
}
