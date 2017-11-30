package edu.utah.cs4710.rusty.base_ic;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rusty on 11/15/2017.
 */

class IOService implements Serializable{
    Integer service_number;
    String direction;
    Service service;
    String service_name;

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public Integer getService_number() {
        return service_number;
    }

    public void setService_number(Integer service_number) {
        this.service_number = service_number;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
