package controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;
import model.OrderItem;
import model.OrderModel;

public class CreateOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // Parse JSON request body
            BufferedReader reader = request.getReader();
            Gson gson = new Gson();
            OrderRequest orderRequest = gson.fromJson(reader, OrderRequest.class);

            // Business logic
            OrderModel orderModel = new OrderModel();
            int orderId = orderModel.createOrder(
                orderRequest.getOrderDate(),
                orderRequest.getCustomerId(),
                orderRequest.getShippingId(),
                orderRequest.getBillingId(),
                orderRequest.getItems()
            );

            // Send response
            if (orderId > 0) {
                response.setStatus(HttpServletResponse.SC_CREATED);
                out.println("{\"message\": \"Order created successfully\", \"order_id\": " + orderId + "}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("{\"error\": \"Failed to create order\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("{\"error\": \"Internal server error\"}");
        }
    }
}
