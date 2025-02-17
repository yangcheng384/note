package com.misc.note.auth.feign;

import com.misc.note.auth.components.FeignDecoder;
import com.misc.note.auth.config.FeignConfig;
import com.misc.note.auth.fallback.AccountFeignFallbackFactory;
import com.misc.note.common.domain.AccountVO;
import com.misc.note.common.resp.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "account", fallback = AccountFeignFallbackFactory.class)
public interface AccountFeignClient {

    @GetMapping("/account/getAccountByContact/{contact}")
    ResultVO<AccountVO> getAccountByContact(@PathVariable("contact") String contact);
}
