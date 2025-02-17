package com.misc.note.auth.fallback;

import com.misc.note.auth.feign.AccountFeignClient;
import com.misc.note.common.domain.AccountVO;
import com.misc.note.common.resp.ResultUtil;
import com.misc.note.common.resp.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountFeignFallbackFactory implements FallbackFactory<AccountFeignClient> {

    @Override
    public AccountFeignClient create(Throwable cause) {
        return new AccountFeignClient() {

            @Override
            public ResultVO<AccountVO> getAccountByContact(String contact) {
                log.error("调用/account/getAccountByContact接口异常!", cause);
                return ResultUtil.failure();
            }
        };
    }
}
