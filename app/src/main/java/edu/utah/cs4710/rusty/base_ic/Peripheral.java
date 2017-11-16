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
}
