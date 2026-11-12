package com.shopflow;

public class TestOrderRead {

    public static void main(String[] args) {

        OrderReadDAO dao = new OrderReadDAO();

        dao.displayOrder(2);
        
        AllOrdersDAO allDao = new AllOrdersDAO();
        allDao.displayAllOrders();
    }
}
