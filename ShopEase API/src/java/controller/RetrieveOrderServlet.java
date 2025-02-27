package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import model.OrderModel;
import com.google.gson.Gson;
import java.util.Map;

public class RetrieveOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            int orderId = Integer.parseInt(request.getParameter("order_id"));
            OrderModel orderModel = new OrderModel();
            Map<String, Object> orderDetails = orderModel.getOrderDetails(orderId);

            if (orderDetails != null) {
                response.setStatus(HttpServletResponse.SC_OK);
                Gson gson = new Gson();
                out.println(gson.toJson(orderDetails));
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
