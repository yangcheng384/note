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
import com.misc.note.account.entity.PieceComment;
import com.misc.note.account.service.PieceCommentService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 作品评论表 控制层。
 *
 * @author 11606
 * @since 2025-02-12
 */
@RestController
@RequestMapping("/pieceComment")
public class PieceCommentController {

    @Autowired
    private PieceCommentService pieceCommentService;

    /**
     * 添加作品评论表。
     *
     * @param pieceComment 作品评论表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody PieceComment pieceComment) {
        return pieceCommentService.save(pieceComment);
    }

    /**
     * 根据主键删除作品评论表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return pieceCommentService.removeById(id);
    }

    /**
     * 根据主键更新作品评论表。
     *
     * @param pieceComment 作品评论表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody PieceComment pieceComment) {
        return pieceCommentService.updateById(pieceComment);
    }

    /**
     * 查询所有作品评论表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<PieceComment> list() {
        return pieceCommentService.list();
    }

    /**
     * 根据作品评论表主键获取详细信息。
     *
     * @param id 作品评论表主键
     * @return 作品评论表详情
     */
    @GetMapping("getInfo/{id}")
    public PieceComment getInfo(@PathVariable Long id) {
        return pieceCommentService.getById(id);
    }

    /**
     * 分页查询作品评论表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<PieceComment> page(Page<PieceComment> page) {
        return pieceCommentService.page(page);
    }

}
