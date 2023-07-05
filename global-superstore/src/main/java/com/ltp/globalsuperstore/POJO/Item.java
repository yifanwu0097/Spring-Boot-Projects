package com.ltp.globalsuperstore.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Data
public class Item {
    private String category;
    private String name;
    private double price;
    private double discount;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date date;
    private String id;

    public Item(){
        this.id= UUID.randomUUID().toString();
    }



}
