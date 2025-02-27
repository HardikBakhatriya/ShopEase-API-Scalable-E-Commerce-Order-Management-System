package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import model.OrderModel;

public class DeleteOrderItemServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            int orderItemSeqId = Integer.parseInt(request.getParameter("order_item_seq_id"));

            OrderModel orderModel = new OrderModel();
            boolean isDeleted = orderModel.deleteOrderItem(orderItemSeqId);

            if (isDeleted) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.println("{\"message\": \"Order item deleted successfully\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("{\"error\": \"Order item not found\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"error\": \"Invalid request\"}");
        }
    }
}
