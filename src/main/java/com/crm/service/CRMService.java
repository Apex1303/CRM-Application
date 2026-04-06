package com.crm.service;

import com.crm.model.Customer;
import java.util.ArrayList;
import java.util.List;

public class CRMService {
    private List<Customer> customers = new ArrayList<>();
    private int nextId = 1;

    public void addCustomer(String name, String email) {
        customers.add(new Customer(nextId++, name, email));
        System.out.println("✅ Customer added successfully!");
    }

    public void listCustomers() {
        if (customers.isEmpty()) {
            System.out.println("⚠️ No customers found.");
        } else {
            System.out.println("\n--- Customer List ---");
            customers.forEach(System.out::println);
        }
    }

    public boolean deleteCustomer(int id) {
        return customers.removeIf(c -> c.getId() == id);
    }
}
