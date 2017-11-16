package edu.utah.cs4710.rusty.base_ic;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rusty on 11/15/2017.
 */

public class PeripheralResponse implements Serializable{
    List<Peripheral> peripherals;
    Boolean success;
}
