package com.misc.note.account.controller;

import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.misc.note.account.entity.PieceCommentLike;
import com.misc.note.account.service.PieceCommentLikeService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 评论点赞表 控制层。
 *
 * @author 11606
 * @since 2025-02-12
 */
@RestController
@RequestMapping("/pieceCommentLike")
public class PieceCommentLikeController {

    @Autowired
    private PieceCommentLikeService pieceCommentLikeService;

    /**
     * 添加评论点赞表。
     *
     * @param pieceCommentLike 评论点赞表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody PieceCommentLike pieceCommentLike) {
        return pieceCommentLikeService.save(pieceCommentLike);
    }

    /**
     * 根据主键删除评论点赞表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return pieceCommentLikeService.removeById(id);
    }

    /**
     * 根据主键更新评论点赞表。
     *
     * @param pieceCommentLike 评论点赞表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody PieceCommentLike pieceCommentLike) {
        return pieceCommentLikeService.updateById(pieceCommentLike);
    }

    /**
     * 查询所有评论点赞表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<PieceCommentLike> list() {
        return pieceCommentLikeService.list();
    }

    /**
     * 根据评论点赞表主键获取详细信息。
     *
     * @param id 评论点赞表主键
     * @return 评论点赞表详情
     */
    @GetMapping("getInfo/{id}")
    public PieceCommentLike getInfo(@PathVariable Long id) {
        return pieceCommentLikeService.getById(id);
    }

    /**
     * 分页查询评论点赞表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<PieceCommentLike> page(Page<PieceCommentLike> page) {
        return pieceCommentLikeService.page(page);
    }

}
