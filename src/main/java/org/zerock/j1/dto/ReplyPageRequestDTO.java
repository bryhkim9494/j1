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
public class ReplyPageRequestDTO extends PageRequestDTO {   // 댓글 페이지 요청 정보를 담는 DTO 클래스입니다. PageRequestDTO를 상속합니다.

    private Long bno;   // 게시글 번호를 저장하는 필드입니다.

    @Builder.Default
    private int page = 1;   // 페이지 번호를 저장하는 필드입니다. 기본값은 1입니다.

    @Builder.Default
    private int size = 20;   // 한 페이지에 표시될 댓글 수를 저장하는 필드입니다. 기본값은 20입니다.

    private boolean last;   // 마지막 페이지 여부를 저장하는 필드입니다.
}
