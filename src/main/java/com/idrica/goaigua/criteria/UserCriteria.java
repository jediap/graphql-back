package com.idrica.goaigua.criteria;

import lombok.Data;

@Data
public class UserCriteria {

    private String dni;
    private String username;
    private Long page;
    private Long limit;
}
