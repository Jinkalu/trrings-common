package com.triings.trringscommon.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -1157517567844712033L;
    private String email;
    private String password;
}
