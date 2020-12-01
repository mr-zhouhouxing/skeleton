package io.pandora.mall.pojo.dto.admin;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Created by John on 2020/9/10
 */
@Data
public class LoginDto implements Serializable {
    private String account;
    private String password;
}
