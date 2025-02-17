package com.misc.note.account.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作品表 实体类。
 *
 * @author 11606
 * @since 2025-02-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("note_piece")
public class Piece implements Serializable {

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
     * 作品类型：0-图文，1-视频
     */
    private String pieceType;

    /**
     * 作品标题
     */
    private String pieceTitle;

    /**
     * 作品内容
     */
    private String pieceContent;

    /**
     * 作品状态: 0-待发布，1-审核中，2-上架，3-下架
     */
    private String pieceStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createName;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    private String updateName;

}
