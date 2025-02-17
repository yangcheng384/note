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
import com.misc.note.account.entity.AccountFocus;
import com.misc.note.account.service.AccountFocusService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 账户关注表 控制层。
 *
 * @author 11606
 * @since 2025-02-12
 */
@RestController
@RequestMapping("/accountFocus")
public class AccountFocusController {

    @Autowired
    private AccountFocusService accountFocusService;

    /**
     * 添加账户关注表。
     *
     * @param accountFocus 账户关注表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody AccountFocus accountFocus) {
        return accountFocusService.save(accountFocus);
    }

    /**
     * 根据主键删除账户关注表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return accountFocusService.removeById(id);
    }

    /**
     * 根据主键更新账户关注表。
     *
     * @param accountFocus 账户关注表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody AccountFocus accountFocus) {
        return accountFocusService.updateById(accountFocus);
    }

    /**
     * 查询所有账户关注表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<AccountFocus> list() {
        return accountFocusService.list();
    }

    /**
     * 根据账户关注表主键获取详细信息。
     *
     * @param id 账户关注表主键
     * @return 账户关注表详情
     */
    @GetMapping("getInfo/{id}")
    public AccountFocus getInfo(@PathVariable Long id) {
        return accountFocusService.getById(id);
    }

    /**
     * 分页查询账户关注表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<AccountFocus> page(Page<AccountFocus> page) {
        return accountFocusService.page(page);
    }

}
