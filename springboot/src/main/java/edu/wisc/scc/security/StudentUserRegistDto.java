package edu.wisc.scc.security;

import edu.wisc.scc.entity.StudentUser;
import lombok.Data;

@Data
public class StudentUserRegistDto extends StudentUser {
    String validateCode;
    String validateCodeKey;
}
