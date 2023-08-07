package org.zerock.j1.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data   // Lombok을 사용하여 Getter, Setter 등의 메서드를 자동으로 생성합니다.
@ToString   // toString() 메서드를 자동으로 생성합니다.
public class PageRequestDTO {   // 페이지 요청 정보를 담는 DTO 클래스입니다.

    // @Builder.Default
    private int page = 1;   // 페이지 번호를 저장하는 필드입니다.
    // @Builder.Default
    private int size = 10;   // 한 페이지에 표시될 게시글 수를 저장하는 필드입니다.
    private String type, keyword;   // 검색 유형과 키워드를 저장하는 필드입니다.

    public PageRequestDTO() {   // 기본 생성자입니다. 기본값으로 페이지 1, 게시글 수 10을 설정합니다.
        this(1, 10);
    }

    public PageRequestDTO(int page, int size) {   // 페이지와 게시글 수를 받아 생성하는 생성자입니다.
        this(page, size, null, null);
    }

    public PageRequestDTO(int page, int size, String type, String keyword) {   // 모든 필드를 받아 생성하는 생성자입니다.
        this.page = page <= 0 ? 1 : page;   // 페이지 번호가 0 이하일 경우 1로 설정합니다.
        this.size = size <= 0 || size >= 100 ? 10 : size;   // 게시글 수가 0 이하이거나 100 이상일 경우 10으로 설정합니다.
        this.type = type;   // 검색 유형을 저장합니다.
        this.keyword = keyword;   // 검색 키워드를 저장합니다.
    }
}
