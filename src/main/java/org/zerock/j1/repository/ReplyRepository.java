package org.zerock.j1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.j1.domain.Reply;
// JpaRepository 인터페이스를 확장하는 ReplyRepository 인터페이스 정의
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // 특정 게시물의 댓글 리스트를 페이징하여 조회
    @Query("select r from Reply r where r.board.bno = :bno ")
    Page<Reply> listBoard(@Param("bno") Long bno, Pageable pageable);

    // 특정 게시물의 댓글 수 조회
    @Query("select count(r) from Reply r where r.board.bno = :bno ")
    long getCountBoard(@Param("bno") Long bno); // JPQL에서 나오는 count값은 항상 long임

}
