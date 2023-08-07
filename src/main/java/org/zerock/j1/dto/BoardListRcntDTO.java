package org.zerock.j1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data   // Lombok을 사용하여 Getter, Setter 등의 메서드를 자동으로 생성합니다.
@Builder   // 빌더 패턴을 지원하는 메서드를 자동으로 생성합니다.
@AllArgsConstructor   // 모든 필드를 인자로 받는 생성자를 자동으로 생성합니다.
@NoArgsConstructor   // 기본 생성자를 자동으로 생성합니다.
@ToString   // toString() 메서드를 자동으로 생성합니다.
public class BoardListRcntDTO {   // 최근 게시글 목록 조회 시 사용하는 DTO 클래스입니다.

    private Long bno;   // 게시글 번호를 저장하는 필드입니다.
    private String title;   // 게시글 제목을 저장하는 필드입니다.
    private String writer;   // 게시글 작성자를 저장하는 필드입니다.
    private long replyCount;   // 댓글 수를 저장하는 필드입니다.

}