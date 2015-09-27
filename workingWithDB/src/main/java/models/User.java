package models;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by woqpw on 26.09.15.
 */
public class User {
    private static final AtomicInteger COUNTER = new AtomicInteger();
    private final int id;

    private String username;

    private String password;

    private String uuid;

    public User(String username, String password, String uuid) {
        this.id = COUNTER.getAndIncrement();
        this.username = username;
        this.password = password;
        this.uuid = uuid;
    }
    public User(){
        this.id = COUNTER.getAndIncrement();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
