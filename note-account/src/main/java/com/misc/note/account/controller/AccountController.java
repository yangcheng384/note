package com.misc.note.account.controller;

import com.misc.note.account.entity.AccountRequest;
import com.misc.note.common.domain.AccountVO;
import com.mybatisflex.core.paginate.Page;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.misc.note.account.entity.Account;
import com.misc.note.account.service.AccountService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 账户表 控制层。
 *
 * @author 11606
 * @since 2025-02-12
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    /**
     * 添加账户表。
     *
     * @param accountRequest 账户注册请求
     */
    @PostMapping("register")
    public void register(@RequestBody @Valid AccountRequest accountRequest) {
         accountService.register(accountRequest);
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file")MultipartFile file){
        return accountService.uploadFile(file);
    }

    /**
     * 根据主键删除账户表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return accountService.removeById(id);
    }

    /**
     * 根据主键更新账户表。
     *
     * @param account 账户表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody Account account) {
        return accountService.updateById(account);
    }

    /**
     * 查询所有账户表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<Account> list() {
        return accountService.list();
    }

    /**
     * 根据账户表主键获取详细信息。
     *
     * @param id 账户表主键
     * @return 账户表详情
     */
    @GetMapping("getInfo/{id}")
    public Account getInfo(@PathVariable Long id) {
        return accountService.getById(id);
    }

    @GetMapping("/getAccountByContact/{contact}")
    public AccountVO getAccountByContact(@PathVariable("contact") String contact){
        return accountService.getAccountByContact(contact);
    }

    /**
     * 分页查询账户表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<Account> page(Page<Account> page) {
        return accountService.page(page);
    }

}
