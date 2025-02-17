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
import com.misc.note.account.entity.AccountFans;
import com.misc.note.account.service.AccountFansService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 账户粉丝表 控制层。
 *
 * @author 11606
 * @since 2025-02-12
 */
@RestController
@RequestMapping("/accountFans")
public class AccountFansController {

    @Autowired
    private AccountFansService accountFansService;

    /**
     * 添加账户粉丝表。
     *
     * @param accountFans 账户粉丝表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody AccountFans accountFans) {
        return accountFansService.save(accountFans);
    }

    /**
     * 根据主键删除账户粉丝表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return accountFansService.removeById(id);
    }

    /**
     * 根据主键更新账户粉丝表。
     *
     * @param accountFans 账户粉丝表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody AccountFans accountFans) {
        return accountFansService.updateById(accountFans);
    }

    /**
     * 查询所有账户粉丝表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<AccountFans> list() {
        return accountFansService.list();
    }

    /**
     * 根据账户粉丝表主键获取详细信息。
     *
     * @param id 账户粉丝表主键
     * @return 账户粉丝表详情
     */
    @GetMapping("getInfo/{id}")
    public AccountFans getInfo(@PathVariable Long id) {
        return accountFansService.getById(id);
    }

    /**
     * 分页查询账户粉丝表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<AccountFans> page(Page<AccountFans> page) {
        return accountFansService.page(page);
    }

}
