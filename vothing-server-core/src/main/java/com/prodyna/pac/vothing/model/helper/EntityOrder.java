package com.prodyna.pac.vothing.model.helper;

import com.prodyna.pac.vothing.constants.VothingConstants;

/**
 * Created by jrodan on 08/05/16.
 */
public class EntityOrder implements VothingConstants {

    private String order = ORDER_DEFAULT;

    public EntityOrder() {
    }

    public EntityOrder(String order) {
        if (order != null) {
            // TODO 
            if (order.equals(ORDER_MODIFIED_ASC)) {
                this.order = ORDER_MODIFIED_ASC;
            }
        }
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
