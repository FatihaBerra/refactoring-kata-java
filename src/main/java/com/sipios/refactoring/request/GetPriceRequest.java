package com.sipios.refactoring.request;


import java.util.List;

public class GetPriceRequest {
    private List<ItemRequest> items;
    private String type;

    public GetPriceRequest(List<ItemRequest> items, String t) {
        this.items = items;
        this.type = t;
    }

    public GetPriceRequest() {}

    public List<ItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ItemRequest> items) {
        this.items = items;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
