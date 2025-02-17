package com.misc.note.account.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.misc.note.account.entity.Piece;
import com.misc.note.account.mapper.PieceMapper;
import com.misc.note.account.service.PieceService;
import org.springframework.stereotype.Service;

/**
 * 作品表 服务层实现。
 *
 * @author 11606
 * @since 2025-02-12
 */
@Service
public class PieceServiceImpl extends ServiceImpl<PieceMapper, Piece>  implements PieceService{

}
