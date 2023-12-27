package com.sipios.refactoring.request;

import com.sipios.refactoring.enums.ItemType;

public class ItemRequest {
    private ItemType type;
    private int nb;

    public ItemRequest() {}

    public ItemRequest(ItemType type, int quantity) {
        this.type = type;
        this.nb = quantity;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }
}
