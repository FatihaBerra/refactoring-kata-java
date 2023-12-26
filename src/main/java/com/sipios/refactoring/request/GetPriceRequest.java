package com.sipios.refactoring.request;


public class GetPriceRequest {
    private ItemRequest[] items;
    private String type;

    public GetPriceRequest(ItemRequest[] is, String t) {
        this.items = is;
        this.type = t;
    }

    public GetPriceRequest() {}

    public ItemRequest[] getItems() {
        return items;
    }

    public void setItems(ItemRequest[] items) {
        this.items = items;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
