package pracs1.springboot.search.pagination;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {

    /** 1. 페이지 당 보여지는 게시글의 최대 개수 **/
    private int pageSize = 10;
    /** 2. 페이징된 버튼의 블럭당 최대 개수 **/
    private int blockSize = 10;
    /** 3. 현재 페이지 **/
    private int page = 1;
    /** 4. 현재 블럭 **/
    private int block = 1;
    /** 5. 총 게시글 수 **/
    private int totalListCnt;
    /** 6. 총 페이지 수 **/
    private int totalPageCnt;
    /** 7. 총 블럭 수 **/
    private int totalBlockCnt;
    /** 8. 블럭 시작 페이지 **/
    private int startPage = 1;
    /** 9. 블럭 마지막 페이지 **/
    private int endPage = 1;
    /** 10. DB 접근 시작 index **/
    private int startIndex = 0;
    /** 11. 이전 블럭의 마지막 페이지 **/
    private int prevBlockPage;
    /** 12. 다음 블럭의 시작 페이지 **/
    private int nextBlockPage;

    public Pagination(int totalListCnt, int page) {
        // 총 게시물 수와 현재 페이지를 Controller로 부터 받아옴

        /** 현재 페이지 **/
        setPage(page);
        /** 총 게시글 수 **/
        setTotalListCnt(totalListCnt);
        /** 총 페이지 수 **/
        // 한 페이지의 최대 개수를 총 게시물 수 * 1.0로 나누어주고 올림 해준다.
        // 총 페이지 수 를 구할 수 있다.
        setTotalPageCnt((int) Math.ceil(totalListCnt * 1.0 / pageSize));
        /** 총 블럭 수 **/
        // 한 블럭의 최대 개수를 총  페이지의 수 * 1.0로 나누어주고 올림 해준다.
        // 총 블럭 수를 구할 수 있다.
        setTotalBlockCnt((int) Math.ceil(totalPageCnt * 1.0 / blockSize));
        /** 현재 블럭 **/
        // 현재 페이지 * 1.0을 블록의 최대 개수로 나누어주고 올림한다.
        // 현재 블록을 구할 수 있다.
        setBlock((int) Math.ceil((page * 1.0) / blockSize));
        /** 블럭 시작 페이지 **/
        setStartPage((block - 1) * blockSize + 1);
        /** 블럭 마지막 페이지 **/
        setEndPage(startPage + blockSize - 1);
        /** 이전 블럭(클릭 시, 이전 블럭 마지막 페이지) **/
        setPrevBlockPage((block * blockSize) - blockSize);
        /** 다음 블럭(클릭 시, 다음 블럭 첫번째 페이지) **/
        setNextBlockPage((block * blockSize) + 1);
        /** DB 접근 시작 index **/
        setStartIndex((page - 1) * pageSize);

        validationRun();
    }

    /*
    인덱스 메서드
     */
    public static Pagination indexPaginationCreate(int pageSize) {
        Pagination indexPagination = new Pagination(5, 1);
        indexPagination.setPageSize(pageSize);
        return indexPagination;
    }
    /*
    검증
     */
    public void validationRun(){
        validationEndPage();
        validationNextBlock();
        validationPreBlock();
        validationPostsNotExist();
    }
    public void validationEndPage() {
        if (endPage > totalPageCnt) {
            this.endPage = totalPageCnt;
        }
    }
    public void validationPreBlock() {
        if (prevBlockPage < 1) {
            this.prevBlockPage = 1;
        }
    }
    public void validationNextBlock() {
        if (nextBlockPage > totalPageCnt) {
            nextBlockPage = totalPageCnt;
        }
    }
    //검색 기능 도입시 게시글이 0일 경우 검증
    public void validationPostsNotExist() {
        if (totalListCnt == 0) {
            setTotalPageCnt(1);
            setEndPage(1);
            setNextBlockPage(1);
        }
    }
}
