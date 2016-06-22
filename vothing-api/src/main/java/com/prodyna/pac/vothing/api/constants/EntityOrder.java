package com.prodyna.pac.vothing.api.constants;

/**
 * Created by jrodan on 08/05/16.
 */
public class EntityOrder implements VothingConstants {

	private String order = ORDER_DEFAULT;

	public EntityOrder() {
	}

	public EntityOrder(String order) {
		if (order != null) {
			switch (order) {
				case ORDER_MODIFIED_ASC:
					this.order = ORDER_MODIFIED_ASC;
					break;
				case ORDER_MODIFIED_DESC:
					this.order = ORDER_MODIFIED_DESC;
					break;
				case ORDER_CREATED_ASC:
					this.order = ORDER_CREATED_ASC;
					break;
				case ORDER_CREATED_DESC:
					this.order = ORDER_CREATED_DESC;
					break;
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
