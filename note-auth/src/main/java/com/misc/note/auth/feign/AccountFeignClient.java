package com.misc.note.auth.feign;

import com.misc.note.auth.fallback.AccountFeignFallbackFactory;
import com.misc.note.common.domain.AccountVO;
import com.misc.note.common.resp.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "account", fallback = AccountFeignFallbackFactory.class)
public interface AccountFeignClient {

    @GetMapping("/account/getAccountByPhoneNumber/{phoneNumber}")
    ResultVO<AccountVO> getAccountByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber);
}
