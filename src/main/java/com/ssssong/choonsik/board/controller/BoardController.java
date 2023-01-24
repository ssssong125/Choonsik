package com.ssssong.choonsik.board.controller;

import com.ssssong.choonsik.board.dto.BoardDTO;
import com.ssssong.choonsik.board.service.BoardService;
import com.ssssong.choonsik.common.ResponseDTO;
import com.ssssong.choonsik.common.paging.Pagenation;
import com.ssssong.choonsik.common.paging.ResponseDTOWithPaging;
import com.ssssong.choonsik.common.paging.SelectCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/v2")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {

        this.boardService = boardService;
    }

    @Transactional
    @PostMapping(value = "/board/manage/regist", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
//    @PostMapping(value = "/board/manage/regist", consumes = {"multipart/form-data"})
//    @PostMapping(value = "/board/manage/regist")
    public ResponseEntity<ResponseDTO> registPost(@RequestPart String boardTitle, @RequestPart String memberId, @RequestPart MultipartFile boardImg) {
//    public ResponseEntity<ResponseDTO> registPost(@ModelAttribute BoardDTO boardDTO) {

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardTitle(boardTitle);
        boardDTO.setMemberId(memberId);
        boardDTO.setBoardImage(boardImg);

        log.info(boardDTO.toString());

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "게시글 등록됨", boardService.registPost(boardDTO)));
    }

    @Transactional
    @PutMapping(value = "/board/manage/update", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseDTO> updatePost(@RequestPart long boardCode, @RequestPart String boardTitle, @RequestPart String memberId, @RequestPart MultipartFile boardImg) {

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardCode(boardCode);
        boardDTO.setBoardTitle(boardTitle);
        boardDTO.setMemberId(memberId);
        boardDTO.setBoardImage(boardImg);

        log.info(boardDTO.toString());

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "게시글 수정됨", boardService.updatePost(boardDTO)));
    }

    @Transactional
    @PutMapping("/board/manage/delete")
    public ResponseEntity<ResponseDTO> deletePost(@RequestBody BoardDTO boardDTO) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "게시글 삭제됨", boardService.deletePost(boardDTO.getBoardCode())));
    }

    @GetMapping("/board/list")
    public ResponseEntity<ResponseDTO> selectPostListWithPaging(@RequestParam(name = "offset", defaultValue = "1") String offset) {

        log.info("[ProductController] selectPostListWithPaging : " + offset);

        int totalCount = boardService.selectBoardTotal();
        int limit = 6;
        int buttonAmount = 5;
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(Integer.parseInt(offset), totalCount, limit, buttonAmount);;

        log.info("[ProductController] selectCriteria : " + selectCriteria);

        ResponseDTOWithPaging responseDtoWithPaging = new ResponseDTOWithPaging();
        responseDtoWithPaging.setPageInfo(selectCriteria);
//        responseDtoWithPaging.setData(boardService.selectProductListWithPaging(selectCriteria));
        responseDtoWithPaging.setData(boardService.selectBoardListWithPaging(selectCriteria));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
    }

//    @GetMapping("/products")
//    public ResponseEntity<ResponseDTO> selectProductListWithPaging(@RequestParam(name="offset", defaultValue="1") String offset) {
//
//        log.info("[ProductController] selectProductListWithPaging : " + offset);
//
//        int totalCount = productService.selectProductTotal();
//        int limit = 10;
//        int buttonAmount = 5;
//        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(Integer.parseInt(offset), totalCount, limit, buttonAmount);;
//
//        log.info("[ProductController] selectCriteria : " + selectCriteria);
//
//        ResponseDTOWithPaging responseDtoWithPaging = new ResponseDTOWithPaging();
//        responseDtoWithPaging.setPageInfo(selectCriteria);
//        responseDtoWithPaging.setData(productService.selectProductListWithPaging(selectCriteria));
//
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
//    }
//
//    @GetMapping("/products-management")
//    public ResponseEntity<ResponseDTO> selectProductListWithPagingForAdmin(@RequestParam(name="offset", defaultValue="1") String offset) {
//
//        log.info("[ProductController] selectProductListWithPagingForAdmin : " + offset);
//
//        int totalCount = productService.selectProductTotalForAdmin();
//        int limit = 10;
//        int buttonAmount = 5;
//        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(Integer.parseInt(offset), totalCount, limit, buttonAmount);;
//
//        log.info("[ProductController] selectCriteria : " + selectCriteria);
//
//        ResponseDTOWithPaging responseDtoWithPaging = new ResponseDTOWithPaging();
//        responseDtoWithPaging.setPageInfo(selectCriteria);
//        responseDtoWithPaging.setData(productService.selectProductListWithPagingForAdmin(selectCriteria));
//
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
//    }
//
//    @GetMapping("/products-management/{productCode}")
//    public ResponseEntity<ResponseDTO> selectProductDetailForAdmin(@PathVariable String productCode) {
//
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 상세정보 조회 성공",  productService.selectProductForAdmin(productCode)));
//    }
//
//    @GetMapping("/products/meals")
//    public ResponseEntity<ResponseDTO> selectProductListAboutMeal() {
//
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectProductListAboutMeal()));
//    }
//
//    @GetMapping("/products/dessert")
//    public ResponseEntity<ResponseDTO> selectProductListAboutDessert() {
//
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectProductListAboutDessert()));
//    }
//
//    @GetMapping("/products/beverage")
//    public ResponseEntity<ResponseDTO> selectProductListAboutBeverage() {
//
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectProductListAboutBeverage()));
//    }
//
//    @GetMapping("/products/search")
//    public ResponseEntity<ResponseDTO> selectSearchList(@RequestParam(name="s", defaultValue="all") String search) {
//
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectSearchProductList(search)));
//    }
//
//    @GetMapping("/products/{productCode}")
//    public ResponseEntity<ResponseDTO> selectProductDetail(@PathVariable String productCode) {
//
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 상세정보 조회 성공",  productService.selectProduct(productCode)));
//    }
//

}

//    @PostMapping(value = "/products")
//    public ResponseEntity<ResponseDTO> insertProduct(@ModelAttribute ProductDTO productDto) {
//        log.info("[ProductController] PostMapping productDto : " + productDto);
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 입력 성공",  productService.insertProduct(productDto)));
//    }

//    @PutMapping(value = "/products")
//    public ResponseEntity<ResponseDTO> updateProduct(@ModelAttribute ProductDTO productDto) {
//        log.info("[ProductController]PutMapping productDto : " + productDto);
//
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 업데이트 성공",  productService.updateProduct(productDto)));
//    }