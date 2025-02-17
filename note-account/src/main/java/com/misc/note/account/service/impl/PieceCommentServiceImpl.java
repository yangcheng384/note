package com.misc.note.account.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.misc.note.account.entity.PieceComment;
import com.misc.note.account.mapper.PieceCommentMapper;
import com.misc.note.account.service.PieceCommentService;
import org.springframework.stereotype.Service;

/**
 * 作品评论表 服务层实现。
 *
 * @author 11606
 * @since 2025-02-12
 */
@Service
public class PieceCommentServiceImpl extends ServiceImpl<PieceCommentMapper, PieceComment>  implements PieceCommentService{

}
