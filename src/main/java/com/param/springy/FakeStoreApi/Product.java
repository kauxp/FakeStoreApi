package com.param.springy.FakeStoreApi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Product {
    public int id;
    public String title;
    public int price;
    public String category;
    public String description;
    public String image;
}
