package edu.utah.cs4710.rusty.base_ic;

import java.io.Serializable;

/**
 * Created by Rusty on 11/15/2017.
 */

public class Service implements Serializable{
    Integer id;
    String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
