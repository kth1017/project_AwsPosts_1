package pracs1.springboot.search.object;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pracs1.springboot.domain.posts.Posts;
import pracs1.springboot.domain.posts.PostsRepository;
import pracs1.springboot.pagination.Pagination;
import pracs1.springboot.web.dto.PostsListResponseDto;

import javax.persistence.Entity;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class PostsSearchVo {

    private final PostsRepository postsRepository;

    private int page;
    private int totalListCnt = 10;

    private int startIndex = 0;
    private int pageSize = 10;

    private String type = "title";
    private String keyword = "";

    private List<Posts> searchPostsList;

    public SearchResultDto findSearchPostsList(int page, String type, String keyword) {
        this.page = page;
        this.type = type;
        this.keyword = keyword;

        switch (type) {
            case "title":
                this.searchPostsList = postsRepository.findByTitleContaining(keyword);
                break;
            case "content":
                this.searchPostsList = postsRepository.findByContentContaining(keyword);
                break;
            case "author":
                this.searchPostsList = postsRepository.findByAuthorContaining(keyword);
                break;
        }

        totalListCnt = searchPostsList.size();
        Pagination pagination = new Pagination(totalListCnt, page);
        int startindex = pagination.getStartIndex();
        int pageSize = pagination.getPageSize();

        List<PostsListResponseDto> searchPostsListPaging = searchPostsList.stream()
                .sorted(Comparator.comparing(Posts::getId).reversed())
                .skip(startindex)
                .limit(pageSize)
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());

        SearchResultDto searchResultDto = new SearchResultDto(searchPostsListPaging, pagination, type, keyword);

        return searchResultDto;
    }
}
