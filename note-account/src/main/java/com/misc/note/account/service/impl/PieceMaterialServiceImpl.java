package com.misc.note.account.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.misc.note.account.entity.PieceMaterial;
import com.misc.note.account.mapper.PieceMaterialMapper;
import com.misc.note.account.service.PieceMaterialService;
import org.springframework.stereotype.Service;

/**
 * 作品素材表 服务层实现。
 *
 * @author 11606
 * @since 2025-02-12
 */
@Service
public class PieceMaterialServiceImpl extends ServiceImpl<PieceMaterialMapper, PieceMaterial>  implements PieceMaterialService{

}
