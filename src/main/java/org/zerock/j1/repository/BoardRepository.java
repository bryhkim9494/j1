package org.zerock.j1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.j1.domain.Board;
import org.zerock.j1.dto.BoardReadDTO;
import org.zerock.j1.repository.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

    // 게시물 하나 조회하는 메소드
    @Query("select b from Board b where b.bno = :bno")
    BoardReadDTO readOne(@Param("bno") Long bno);

    // 제목에 특정 키워드를 포함하는 게시물 리스트 조회하는 메소드
    List<Board> findByTitleContaining(String title);

    // 제목에 특정 키워드를 포함하는 게시물 리스트 조회하는 메소드(위에 findByTitleContaining메소드랑 같은 역할을 함)
    @Query("select b from Board b where b.title like %:title% ")
    List<Board> listTitle(@Param("title") String title);

    // 제목에 특정 키워드를 포함하는 게시물 리스트 조회하는 메소드 (튜플 형태)
    @Query("select b.bno,b.title from Board b where b.title like %:title% ")
    List<Object[]> listTitle2(@Param("title") String title);

    // 제목에 특정 키워드를 포함하는 게시물 리스트를 페이징하여 조회하는 메소드 (튜플 형태)
    @Query("select b.bno,b.title from Board b where b.title like %:title% ")
    Page<Object[]> listTitle3(@Param("title") String title, Pageable pageable);

    // 제목 수정하는 메소드
    @Modifying
    @Query("update Board b set b.title = :title where b.bno = :bno")
    int modifyTitle(@Param("title") String title, @Param("bno") Long bno);

    // 내용에 특정 키워드를 포함하는 게시물 리스트를 페이징하여 조회하는 메소드
    Page<Board> findByContentContaining(String content, Pageable pageable);

    // 네이티브 SQL을 사용하여 게시물 리스트 조회하는 메소드
    @Query(value = "select * from t_board ", nativeQuery = true)
    List<Object[]> listNative();

    // 게시물과 댓글 수를 함께 조회하는 메소드
    @Query("select b.bno, b.title, b.writer, count(r) from Board b left outer join Reply r on r.board = b group by b order by b.bno desc")
    List<Object[]> getListWithRcnt();
}
