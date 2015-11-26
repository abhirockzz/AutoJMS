package com.abhirockzz.autojms.order.biz.boundary;

import com.abhirockzz.autojms.order.biz.control.OrderFacade;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author abhirockzz.wordpress.com
 */
@Path("order")
public class OrdersResource {

    @Inject
    OrderFacade orderService;

    @POST
    public Response send(final String order) {
        String id = orderService.send(order);

        return Response.ok(id).build();

    }
}
