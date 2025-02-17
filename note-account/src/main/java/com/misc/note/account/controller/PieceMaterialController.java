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
import com.misc.note.account.entity.PieceMaterial;
import com.misc.note.account.service.PieceMaterialService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 作品素材表 控制层。
 *
 * @author 11606
 * @since 2025-02-12
 */
@RestController
@RequestMapping("/pieceMaterial")
public class PieceMaterialController {

    @Autowired
    private PieceMaterialService pieceMaterialService;

    /**
     * 添加作品素材表。
     *
     * @param pieceMaterial 作品素材表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody PieceMaterial pieceMaterial) {
        return pieceMaterialService.save(pieceMaterial);
    }

    /**
     * 根据主键删除作品素材表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return pieceMaterialService.removeById(id);
    }

    /**
     * 根据主键更新作品素材表。
     *
     * @param pieceMaterial 作品素材表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody PieceMaterial pieceMaterial) {
        return pieceMaterialService.updateById(pieceMaterial);
    }

    /**
     * 查询所有作品素材表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<PieceMaterial> list() {
        return pieceMaterialService.list();
    }

    /**
     * 根据作品素材表主键获取详细信息。
     *
     * @param id 作品素材表主键
     * @return 作品素材表详情
     */
    @GetMapping("getInfo/{id}")
    public PieceMaterial getInfo(@PathVariable Long id) {
        return pieceMaterialService.getById(id);
    }

    /**
     * 分页查询作品素材表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<PieceMaterial> page(Page<PieceMaterial> page) {
        return pieceMaterialService.page(page);
    }

}
