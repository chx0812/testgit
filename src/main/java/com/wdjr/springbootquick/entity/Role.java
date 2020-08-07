package com.wdjr.springbootquick.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Role {


    private  int id;
    private  String name;

    private List<menu> menuList;
}
