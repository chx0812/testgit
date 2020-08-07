package com.wdjr.springbootquick.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class User {

    private  int id;
    private  String name;
    private  String pwd;

    private  List<String> roleList;

    private  List<String> authList;


    private List<Role> r;

}
