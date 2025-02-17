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
import com.misc.note.account.entity.PieceCollect;
import com.misc.note.account.service.PieceCollectService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 作品收藏表 控制层。
 *
 * @author 11606
 * @since 2025-02-12
 */
@RestController
@RequestMapping("/pieceCollect")
public class PieceCollectController {

    @Autowired
    private PieceCollectService pieceCollectService;

    /**
     * 添加作品收藏表。
     *
     * @param pieceCollect 作品收藏表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody PieceCollect pieceCollect) {
        return pieceCollectService.save(pieceCollect);
    }

    /**
     * 根据主键删除作品收藏表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return pieceCollectService.removeById(id);
    }

    /**
     * 根据主键更新作品收藏表。
     *
     * @param pieceCollect 作品收藏表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody PieceCollect pieceCollect) {
        return pieceCollectService.updateById(pieceCollect);
    }

    /**
     * 查询所有作品收藏表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<PieceCollect> list() {
        return pieceCollectService.list();
    }

    /**
     * 根据作品收藏表主键获取详细信息。
     *
     * @param id 作品收藏表主键
     * @return 作品收藏表详情
     */
    @GetMapping("getInfo/{id}")
    public PieceCollect getInfo(@PathVariable Long id) {
        return pieceCollectService.getById(id);
    }

    /**
     * 分页查询作品收藏表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<PieceCollect> page(Page<PieceCollect> page) {
        return pieceCollectService.page(page);
    }

}
