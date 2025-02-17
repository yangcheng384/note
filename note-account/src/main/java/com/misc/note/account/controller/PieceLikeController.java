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
import com.misc.note.account.entity.PieceLike;
import com.misc.note.account.service.PieceLikeService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 作品点赞表 控制层。
 *
 * @author 11606
 * @since 2025-02-12
 */
@RestController
@RequestMapping("/pieceLike")
public class PieceLikeController {

    @Autowired
    private PieceLikeService pieceLikeService;

    /**
     * 添加作品点赞表。
     *
     * @param pieceLike 作品点赞表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody PieceLike pieceLike) {
        return pieceLikeService.save(pieceLike);
    }

    /**
     * 根据主键删除作品点赞表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return pieceLikeService.removeById(id);
    }

    /**
     * 根据主键更新作品点赞表。
     *
     * @param pieceLike 作品点赞表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody PieceLike pieceLike) {
        return pieceLikeService.updateById(pieceLike);
    }

    /**
     * 查询所有作品点赞表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<PieceLike> list() {
        return pieceLikeService.list();
    }

    /**
     * 根据作品点赞表主键获取详细信息。
     *
     * @param id 作品点赞表主键
     * @return 作品点赞表详情
     */
    @GetMapping("getInfo/{id}")
    public PieceLike getInfo(@PathVariable Long id) {
        return pieceLikeService.getById(id);
    }

    /**
     * 分页查询作品点赞表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<PieceLike> page(Page<PieceLike> page) {
        return pieceLikeService.page(page);
    }

}
