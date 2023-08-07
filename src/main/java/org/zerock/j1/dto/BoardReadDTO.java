package org.zerock.j1.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface BoardReadDTO {   // 게시글 조회 시 사용하는 DTO 인터페이스입니다. JOIN 작업에 유용하게 활용될 수 있습니다. JOIN을 많이쓰면 이 방식이 좋음

    Long getBno();   // 게시글 번호를 반환하는 메서드입니다.
    String getTitle();   // 게시글 제목을 반환하는 메서드입니다.
    String getContent();   // 게시글 내용을 반환하는 메서드입니다.

    @JsonFormat(pattern = "yyyy-MM-dd MM:mm:ss")   // JSON 직렬화 시 날짜 형식을 지정합니다.
    LocalDateTime getRegDate();   // 게시글 등록일자를 반환하는 메서드입니다.

    @JsonFormat(pattern = "yyyy-MM-dd MM:mm:ss")   // JSON 직렬화 시 날짜 형식을 지정합니다.
    LocalDateTime getModDate();   // 게시글 수정일자를 반환하는 메서드입니다.

}
