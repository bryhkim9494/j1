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
import org.zerock.j1.domain.Todo;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.TodoDTO;
import org.zerock.j1.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    private final ModelMapper modelMapper;

    // Todo목록을 조회하여 PageResponseDTO로 반환하는 메소드
    @Override
    public PageResponseDTO<TodoDTO> getList() {

        // 페이징 처리를 위한 Pageable 객체 생성
        Pageable pageable = PageRequest.of(0, 20, Sort.by("tno").descending());

        // TodoRepository를 활용하여 페이징 처리된 Todo목록 조회
        Page<Todo> result = todoRepository.findAll(pageable);

        // 조회 결과로부터 Todo엔티티 리스트를 TodoDTO 리스트로 변환하여 반환
        List<TodoDTO> dtoList = result.getContent().stream().map(todo -> modelMapper.map(todo, TodoDTO.class))
                .collect(Collectors.toList());
        // 위에 코드를 해석하면 todo를 dto로 바꾼다음에 list로 만들라는 코드임

//         PageResponseDTO<TodoDTO> response = new PageResponseDTO<>();
//         response.setDtoList(dtoList);
//         return response;

        PageResponseDTO<TodoDTO> response = new PageResponseDTO<>(dtoList, result.getTotalElements(), new PageRequestDTO(1, 20));
        response.setDtoList(dtoList);
        return response;

//        return null;
    }

    @Override
    public TodoDTO register(TodoDTO dto) {

        Todo entity = modelMapper.map(dto, Todo.class);
        Todo result = todoRepository.save(entity);

        return modelMapper.map(result, TodoDTO.class);
    }

    @Override
    public TodoDTO getOne(Long tno) {
        Optional<Todo> result = todoRepository.findById(tno); // Optional에 대해 알아두기
        Todo todo = result.orElseThrow();
        TodoDTO dto = modelMapper.map(todo, TodoDTO.class);
        return dto;
    }

    @Override
    public void remove(Long tno) {

        todoRepository.deleteById(tno);
    }

    @Override
    public void modify(TodoDTO dto) {

        Optional<Todo> result = todoRepository.findById(dto.getTno());

        Todo todo = result.orElseThrow();

        todo.changeTitle(dto.getTitle());

        todoRepository.save(todo);
    }

}
