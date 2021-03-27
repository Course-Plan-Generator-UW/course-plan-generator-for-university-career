package edu.wisc.scc.security;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ModifyPasswordDto {
    private String userId;
    @NotBlank(message = "is blank")
    @Size(min = 6,max = 32,message = "密码长度不得低于6位")
    private String oldPassword;
    @Size(min = 6,max = 32,message = "密码长度不得低于6位")
    @NotBlank(message = "is blank")
    private String newPassword;
}
