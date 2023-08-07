package org.zerock.j1.dto;

import lombok.Data;

// 만든이유: 서비스에서 리턴타입으로 ReplyDTO을 이용하려고

@Data   // Lombok을 사용하여 Getter, Setter 등의 메서드를 자동으로 생성합니다.
public class ReplyDTO {   // 댓글 정보를 담는 DTO 클래스입니다. 서비스에서 리턴 타입으로 활용됩니다.

    private Long rno;   // 댓글 번호를 저장하는 필드입니다.
    private String replyText;   // 댓글 내용을 저장하는 필드입니다.
    private String replyFile;   // 댓글 파일을 저장하는 필드입니다.
    private String replyer;   // 댓글 작성자를 저장하는 필드입니다.
    private Long bno;   // 게시글 번호를 저장하는 필드입니다.
}