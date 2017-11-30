package edu.utah.cs4710.rusty.base_ic;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rusty on 11/15/2017.
 */

public class Peripheral implements Serializable{
    Integer id;
    String address;
    String queue;
    String name;
    List<IOService> input_services;
    List<IOService> output_services;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IOService> getInput_services() {
        return input_services;
    }

    public void setInput_services(List<IOService> input_services) {
        this.input_services = input_services;
    }

    public List<IOService> getOutput_services() {
        return output_services;
    }

    public void setOutput_services(List<IOService> output_services) {
        this.output_services = output_services;
    }
}
