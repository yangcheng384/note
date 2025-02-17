package com.misc.note.account.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.misc.note.account.entity.PieceLike;
import com.misc.note.account.mapper.PieceLikeMapper;
import com.misc.note.account.service.PieceLikeService;
import org.springframework.stereotype.Service;

/**
 * 作品点赞表 服务层实现。
 *
 * @author 11606
 * @since 2025-02-12
 */
@Service
public class PieceLikeServiceImpl extends ServiceImpl<PieceLikeMapper, PieceLike>  implements PieceLikeService{

}
