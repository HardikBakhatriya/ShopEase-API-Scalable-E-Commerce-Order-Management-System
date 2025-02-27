package model;

import db.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;.
import java.util.Map;

public class OrderModel {
    public int createOrder(String orderDate, int customerId, int shippingId, int billingId, List<OrderItem> items) throws SQLException {
        Connection conn = DBConnector.getConnection();
        int orderId = -1;

        try {
            // Insert into Order_Header table
            String insertOrderSQL = "INSERT INTO Order_Header (order_date, customer_id, shipping_contact_mech_id, billing_contact_mech_id) VALUES (?, ?, ?, ?)";
            PreparedStatement psOrder = conn.prepareStatement(insertOrderSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            psOrder.setString(1, orderDate);
            psOrder.setInt(2, customerId);
            psOrder.setInt(3, shippingId);
            psOrder.setInt(4, billingId);

            int rowsAffected = psOrder.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = psOrder.getGeneratedKeys();
                if (rs.next()) {
                     orderId = rs.getInt(1); // Retrieve generated order_id
                }
            }

            // Insert into Order_Item table
            if (orderId != -1) {
                String insertItemSQL = "INSERT INTO Order_Item (order_id, product_id, quantity, status) VALUES (?, ?, ?, ?)";
                PreparedStatement psItem = conn.prepareStatement(insertItemSQL);

                for (OrderItem item : items) {
                    psItem.setInt(1, orderId);
                    psItem.setInt(2, item.getProductId());
                    psItem.setInt(3, item.getQuantity());
                    psItem.setString(4, item.getStatus());
                    psItem.addBatch();
                }
                psItem.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return orderId;
    }
    
    public Map<String, Object> getOrderDetails(int orderId) throws SQLException {
    Connection conn = DBConnector.getConnection();
    Map<String, Object> orderDetails = new HashMap<>();

    try {
        // Fetch order details
        String orderQuery = "SELECT * FROM Order_Header WHERE order_id = ?";
        PreparedStatement psOrder = conn.prepareStatement(orderQuery);
        psOrder.setInt(1, orderId);
        ResultSet rsOrder = psOrder.executeQuery();

        if (rsOrder.next()) {
            orderDetails.put("order_id", rsOrder.getInt("order_id"));
            orderDetails.put("order_date", rsOrder.getDate("order_date"));
            orderDetails.put("customer_id", rsOrder.getInt("customer_id"));
            orderDetails.put("shipping_contact_mech_id", rsOrder.getInt("shipping_contact_mech_id"));
            orderDetails.put("billing_contact_mech_id", rsOrder.getInt("billing_contact_mech_id"));

            // Fetch order items
            String itemsQuery = "SELECT * FROM Order_Item WHERE order_id = ?";
            PreparedStatement psItems = conn.prepareStatement(itemsQuery);
            psItems.setInt(1, orderId);
            ResultSet rsItems = psItems.executeQuery();

            List<Map<String, Object>> items = new ArrayList<>();
            while (rsItems.next()) {
                Map<String, Object> item = new HashMap<>();
                item.put("order_item_seq_id", rsItems.getInt("order_item_seq_id"));
                item.put("product_id", rsItems.getInt("product_id"));
                item.put("quantity", rsItems.getInt("quantity"));
                item.put("status", rsItems.getString("status"));
                items.add(item);
            }
            orderDetails.put("items", items);
        } else {
            return null; // No order found
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    }
    return orderDetails;
}

public boolean updateOrder(int orderId, int shippingId, int billingId) throws SQLException {
    Connection conn = DBConnector.getConnection();

    try {
        String updateQuery = "UPDATE Order_Header SET shipping_contact_mech_id = ?, billing_contact_mech_id = ? WHERE order_id = ?";
        PreparedStatement ps = conn.prepareStatement(updateQuery);
        ps.setInt(1, shippingId);
        ps.setInt(2, billingId);
        ps.setInt(3, orderId);

        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    }
}

public boolean deleteOrder(int orderId) throws SQLException {
    Connection conn = DBConnector.getConnection();

    try {
        String deleteQuery = "DELETE FROM Order_Header WHERE order_id = ?";
        PreparedStatement ps = conn.prepareStatement(deleteQuery);
        ps.setInt(1, orderId);

        int rowsDeleted = ps.executeUpdate();
        return rowsDeleted > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    }
}

public boolean addOrderItem(int orderId, int productId, int quantity, String status) throws SQLException {
    Connection conn = DBConnector.getConnection();

    try {
        String insertItemSQL = "INSERT INTO Order_Item (order_id, product_id, quantity, status) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(insertItemSQL);
        ps.setInt(1, orderId);
        ps.setInt(2, productId);
        ps.setInt(3, quantity);
        ps.setString(4, status);

        int rowsInserted = ps.executeUpdate();
        return rowsInserted > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    }
}

public boolean updateOrderItem(int orderItemSeqId, int quantity, String status) throws SQLException {
    Connection conn = DBConnector.getConnection();

    try {
        String updateItemSQL = "UPDATE Order_Item SET quantity = ?, status = ? WHERE order_item_seq_id = ?";
        PreparedStatement ps = conn.prepareStatement(updateItemSQL);
        ps.setInt(1, quantity);
        ps.setString(2, status);
        ps.setInt(3, orderItemSeqId);

        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    }
}

public boolean deleteOrderItem(int orderItemSeqId) throws SQLException {
    Connection conn = DBConnector.getConnection();

    try {
        String deleteItemSQL = "DELETE FROM Order_Item WHERE order_item_seq_id = ?";
        PreparedStatement ps = conn.prepareStatement(deleteItemSQL);
        ps.setInt(1, orderItemSeqId);

        int rowsDeleted = ps.executeUpdate();
        return rowsDeleted > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    }
}
    
}
