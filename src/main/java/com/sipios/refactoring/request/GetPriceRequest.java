package com.sipios.refactoring.request;


import com.sipios.refactoring.enums.CustomerType;

import java.util.List;

public class GetPriceRequest {
    private List<ItemRequest> items;
    private CustomerType type;

    public GetPriceRequest(List<ItemRequest> items, CustomerType type) {
        this.items = items;
        this.type = type;
    }

    public GetPriceRequest() {}

    public List<ItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ItemRequest> items) {
        this.items = items;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }
}
