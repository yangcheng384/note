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
 * 账户关注表 实体类。
 *
 * @author 11606
 * @since 2025-02-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("note_account_focus")
public class AccountFocus implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 账户id
     */
    private Long accountId;

    /**
     * 关注账户id
     */
    private Long focusAccountId;

}
