
package com.example.library.model;

import java.util.UUID;

public class Patron {
    private final String id = UUID.randomUUID().toString();
    private String name;
    private String email;

    public Patron(String name, String email) { this.name = name; this.email = email; }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    @Override public String toString() { return String.format("Patron{id='%s', name='%s'}", id, name); }
}
