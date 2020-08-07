package com.wdjr.springbootquick.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class menu {


    private  int id;
    private  String name;
    private  String permissions; //权限标识
    private  String htmlUrl;
}
