package org.zerock.j1.dto;

import java.util.List;

import lombok.Data;

@Data
public class PageResponseDTO<E> {

    private List<E> dtoList;

    private long totalCount;

    private List<Integer> pageNums;

    private boolean prev, next;

    private PageRequestDTO requestDTO;

    private int page, size, start, end;

    public PageResponseDTO(List<E> dtoList, long totalCount, PageRequestDTO pageRequestDTO) {
        this.dtoList = dtoList;
        this.totalCount = totalCount;
        this.requestDTO = pageRequestDTO;
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        // 13 -> 1.3 ->2.0
        int tempEnd = (int) (Math.ceil(page / 10.0) * 10);

        this.start = tempEnd - 9;
        this.prev = this.start != 1;

        // 20
        int realEnd = (int) (Math.ceil(totalCount / (double) size));
        this.end = tempEnd > realEnd ? realEnd : tempEnd;
        this.next = (this.end * this.size) < totalCount;

    }

}
