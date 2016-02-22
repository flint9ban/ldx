package com.gp.db.domain;

import lombok.Data;

/**
 * Created by liyi on 2016/2/22.
 */
@Data
public class Column {

    private String name,type,comment;

    private Integer length;

    public Column(String value){
        String[] values =  value.split(",");
        setName(values[0]);
        setType(values[1]);
        setLength(Integer.parseInt(values[2]));
        setComment(values[3]);
    }
}
