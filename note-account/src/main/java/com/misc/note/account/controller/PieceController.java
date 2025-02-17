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
import com.misc.note.account.entity.Piece;
import com.misc.note.account.service.PieceService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 作品表 控制层。
 *
 * @author 11606
 * @since 2025-02-12
 */
@RestController
@RequestMapping("/piece")
public class PieceController {

    @Autowired
    private PieceService pieceService;

    /**
     * 添加作品表。
     *
     * @param piece 作品表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody Piece piece) {
        return pieceService.save(piece);
    }

    /**
     * 根据主键删除作品表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return pieceService.removeById(id);
    }

    /**
     * 根据主键更新作品表。
     *
     * @param piece 作品表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody Piece piece) {
        return pieceService.updateById(piece);
    }

    /**
     * 查询所有作品表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<Piece> list() {
        return pieceService.list();
    }

    /**
     * 根据作品表主键获取详细信息。
     *
     * @param id 作品表主键
     * @return 作品表详情
     */
    @GetMapping("getInfo/{id}")
    public Piece getInfo(@PathVariable Long id) {
        return pieceService.getById(id);
    }

    /**
     * 分页查询作品表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<Piece> page(Page<Piece> page) {
        return pieceService.page(page);
    }

}
