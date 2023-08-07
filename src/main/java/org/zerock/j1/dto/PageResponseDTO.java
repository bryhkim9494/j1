package org.zerock.j1.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;

@Data   // Lombok을 사용하여 Getter, Setter 등의 메서드를 자동으로 생성합니다.
public class PageResponseDTO<E> {   // 페이지 응답 정보를 담는 DTO 클래스입니다. 제네릭을 사용하여 타입을 유연하게 지정할 수 있습니다.

    private List<E> dtoList;   // DTO 목록을 저장하는 필드입니다.
    private long totalCount;   // 전체 게시글 수를 저장하는 필드입니다.
    private List<Integer> pageNums;   // 페이지 번호 목록을 저장하는 필드입니다.
    private boolean prev, next;   // 이전 페이지, 다음 페이지 여부를 저장하는 필드입니다.
    private PageRequestDTO requestDTO;   // 페이지 요청 정보를 저장하는 필드입니다.
    private int page, size, start, end;   // 페이지 정보 및 범위를 저장하는 필드입니다.

    public PageResponseDTO(List<E> dtoList, long totalCount, PageRequestDTO pageRequestDTO) {
        this.dtoList = dtoList;   // DTO 목록을 초기화합니다.
        this.totalCount = totalCount;   // 전체 게시글 수를 초기화합니다.
        this.requestDTO = pageRequestDTO;   // 페이지 요청 정보를 초기화합니다.
        this.page = pageRequestDTO.getPage();   // 페이지 번호를 초기화합니다.
        this.size = pageRequestDTO.getSize();   // 한 페이지에 표시될 게시글 수를 초기화합니다.

        // 현재 페이지 기준으로 10 단위의 범위를 계산합니다.
        int tempEnd = (int) (Math.ceil(page / 10.0) * 10);

        this.start = tempEnd - 9;   // 시작 페이지 번호를 계산합니다.
        this.prev = this.start != 1;   // 이전 페이지 여부를 계산합니다.

        // 전체 페이지 수를 계산합니다.
        int realEnd = (int) (Math.ceil(totalCount / (double) size));

        this.end = tempEnd > realEnd ? realEnd : tempEnd;   // 끝 페이지 번호를 계산합니다.

        this.next = (this.end * this.size) < totalCount;   // 다음 페이지 여부를 계산합니다.

        // 시작 페이지부터 끝 페이지까지의 번호를 생성하여 pageNums에 저장합니다.
        this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
    }

}
