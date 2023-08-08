package org.zerock.j1.service;

import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.TodoDTO;

import jakarta.transaction.Transactional;

@Transactional // spring꺼 써도되고 jakarta써도 상관없음
public interface TodoService {

    // Todo목록을 조회하여 PageResponseDTO로 반환하는 메소드
    PageResponseDTO<TodoDTO> getList();

    // Todo를 등록하고 등록된 Todo정보를 TodoDTO로 반환하는 메소드
    TodoDTO register(TodoDTO dto);

    // 특정 Todo의 상세 정보를 조회하여 TodoDTO로 반환하는 메소드
    TodoDTO getOne(Long tno);

    // 특정 Todo를 삭제하는 메소드
    void remove(Long tno);

    // Todo의 내용을 수정하는 메소드
    void modify(TodoDTO dto);


}
