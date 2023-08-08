package org.zerock.j1.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.j1.domain.Reply;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.ReplyDTO;
import org.zerock.j1.dto.ReplyPageRequestDTO;
import org.zerock.j1.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

// 서비스 계층을 나타내는 Service 어노테이션 및 필수적인 의존성 주입을 위한 RequiredArgsConstructor 어노테이션
@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    private final ModelMapper modelMapper;

    // 댓글 목록을 조회하여 PageResponseDTO로 반환하는 메소드
    @Override
    public PageResponseDTO<ReplyDTO> list(ReplyPageRequestDTO requestDTO) {

        boolean last = requestDTO.isLast();
        int pageNum = requestDTO.getPage();

        // 페이징 처리 시 마지막 페이지인지 확인하여 전체 댓글 수를 계산하는 부분
        if (last) {
            long totalCount = replyRepository.getCountBoard(requestDTO.getBno());

            pageNum = (int) Math.ceil(totalCount / (double) requestDTO.getSize());

            pageNum = pageNum <= 0 ? 1 : pageNum;
        }

        // 페이징 처리를 위한 Pageable 객체 생성
        Pageable pageable = PageRequest.of(pageNum - 1, requestDTO.getSize(), Sort.by("rno").ascending());

        // ReplyRepository를 활용하여 특정 게시물의 댓글 목록을 페이징하여 조회
        Page<Reply> result = replyRepository.listBoard(requestDTO.getBno(), pageable);

        log.info("----------------------------------------------");

        // log.info(result.getNumber());
        // log.info(result.getContent());

        // 조회 결과로부터 전체 댓글 수와 댓글 DTO 리스트를 생성하여 PageResponseDTO로 반환
        long totalReplyCount = result.getTotalElements();

        List<ReplyDTO> dtoList = result.get().map(en -> modelMapper.map(en, ReplyDTO.class))
                .collect(Collectors.toList());
        PageResponseDTO<ReplyDTO> responseDTO = new PageResponseDTO<>(dtoList, totalReplyCount, requestDTO);
        responseDTO.setPage(pageNum);
        return responseDTO;
    }

    // 댓글 등록 메소드
    @Override
    public Long register(ReplyDTO replyDTO) {

        Reply reply = modelMapper.map(replyDTO, Reply.class);
        log.info("reply ....");
        log.info(reply);

        // ReplyRepository를 활용하여 댓글을 저장하고, 저장된 댓글의 rno를 반환
        Long newRno = replyRepository.save(reply).getRno();

        return newRno;
    }

    // 특정 댓글의 상세 정보를 조회하여 ReplyDTO로 반환하는 메소드
    @Override
    public ReplyDTO read(Long rno) {

        Optional<Reply> result = replyRepository.findById(rno);
        Reply reply = result.orElseThrow();
        return modelMapper.map(reply, ReplyDTO.class);
    }

    // 특정 댓글을 삭제하는 메소드
    @Override
    public void remove(Long rno) {

        Optional<Reply> result = replyRepository.findById(rno);
        Reply reply = result.orElseThrow();

        // 댓글의 내용과 파일 정보를 변경하여 댓글을 삭제 처리
        reply.changeText("해당 글은 삭제되었습니다.");
        reply.changeFile(null);
        replyRepository.save(reply);
    }

    // 특정 댓글의 내용과 파일 정보를 수정하는 메소드
    @Override
    public void modify(ReplyDTO replyDTO) {

        Optional<Reply> result = replyRepository.findById(replyDTO.getRno());
        Reply reply = result.orElseThrow();

        // 댓글의 내용과 파일 정보를 수정하여 저장
        reply.changeText(replyDTO.getReplyText());
        reply.changeFile(replyDTO.getReplyFile());
        replyRepository.save(reply);
    }

}
