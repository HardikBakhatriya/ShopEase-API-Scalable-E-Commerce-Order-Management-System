package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import model.OrderModel;

public class DeleteOrderServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            int orderId = Integer.parseInt(request.getParameter("order_id"));

            OrderModel orderModel = new OrderModel();
            boolean isDeleted = orderModel.deleteOrder(orderId);

            if (isDeleted) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.println("{\"message\": \"Order deleted successfully\"}");
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
