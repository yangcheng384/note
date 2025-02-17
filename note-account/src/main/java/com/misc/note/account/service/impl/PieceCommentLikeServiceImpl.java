package com.misc.note.account.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.misc.note.account.entity.PieceCommentLike;
import com.misc.note.account.mapper.PieceCommentLikeMapper;
import com.misc.note.account.service.PieceCommentLikeService;
import org.springframework.stereotype.Service;

/**
 * 评论点赞表 服务层实现。
 *
 * @author 11606
 * @since 2025-02-12
 */
@Service
public class PieceCommentLikeServiceImpl extends ServiceImpl<PieceCommentLikeMapper, PieceCommentLike>  implements PieceCommentLikeService{

}
