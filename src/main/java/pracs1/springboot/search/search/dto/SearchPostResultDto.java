package pracs1.springboot.search.search.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pracs1.springboot.search.pagination.Pagination;
import pracs1.springboot.web.dto.PostsListResponseDto;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class SearchPostResultDto {

    // 인덱스 컨트롤러 > 뷰
    private final List<PostsListResponseDto> searchPostsListPaging;
    private final Pagination pagination;

    // 페이징시 쿼리 파라미터
    private final String type;
    private final String keyword;

}
