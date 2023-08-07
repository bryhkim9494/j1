package org.zerock.j1.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data   // Lombok을 사용하여 Getter, Setter 등의 메서드를 자동으로 생성합니다.
@AllArgsConstructor   // 모든 필드를 인자로 받는 생성자를 자동으로 생성합니다.
@NoArgsConstructor   // 기본 생성자를 자동으로 생성합니다.
@Builder   // 빌더 패턴을 지원하는 메서드를 자동으로 생성합니다.
@ToString   // toString() 메서드를 자동으로 생성합니다.
public class BoardDTO {   // 게시글 정보를 전달하는 DTO(Data Transfer Object) 클래스입니다.

    private Long bno;   // 게시글 번호를 저장하는 필드입니다.
    private String title;   // 게시글 제목을 저장하는 필드입니다.
    private String content;   // 게시글 내용을 저장하는 필드입니다.
    private String writer;   // 게시글 작성자를 저장하는 필드입니다.

    @JsonFormat(pattern = "yyyy-MM-dd MM:mm:ss")   // JSON 직렬화 시 날짜 형식을 지정합니다.
    private LocalDateTime regDate;   // 게시글 등록일자를 저장하는 필드입니다.

    @JsonFormat(pattern = "yyyy-MM-dd MM:mm:ss")   // JSON 직렬화 시 날짜 형식을 지정합니다.
    private LocalDateTime modDate;   // 게시글 수정일자를 저장하는 필드입니다.
}
