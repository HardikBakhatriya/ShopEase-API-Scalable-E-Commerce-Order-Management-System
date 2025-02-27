package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import model.OrderModel;

public class UpdateOrderServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            int orderId = Integer.parseInt(request.getParameter("order_id"));
            int shippingId = Integer.parseInt(request.getParameter("shipping_id"));
            int billingId = Integer.parseInt(request.getParameter("billing_id"));

            OrderModel orderModel = new OrderModel();
            boolean isUpdated = orderModel.updateOrder(orderId, shippingId, billingId);

            if (isUpdated) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.println("{\"message\": \"Order updated successfully\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("{\"error\": \"Order not found\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"error\": \"Invalid request\"}");
        }
    }
}
