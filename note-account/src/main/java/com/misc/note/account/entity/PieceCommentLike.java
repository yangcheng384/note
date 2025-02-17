package com.misc.note.account.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评论点赞表 实体类。
 *
 * @author 11606
 * @since 2025-02-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("note_piece_comment_like")
public class PieceCommentLike implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 作者账户id
     */
    private Long accountId;

    /**
     * 作品id
     */
    private Long pieceId;

    /**
     * 评论id
     */
    private Long commentId;

    /**
     * 评论点赞数
     */
    private Long likeCount;

}
