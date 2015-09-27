package DAO;

import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;
import models.User;

import java.util.List;

/**
 * Created by woqpw on 27.09.15.
 */
public class UserDaoImpl implements UserDAO {

    JDBCClient client;

    public UserDaoImpl(JDBCClient client){
        this.client = client;
    }
    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {
        client.getConnection(res->{
            if(res.succeeded()){
                SQLConnection sqlConnection = res.result();
                // что-то дальше
            } else System.out.println("ress not succede");
        });
    }

    @Override
    public void addUser(User user) {
        client.getConnection(res->{
            if(res.succeeded()){
                SQLConnection sqlConnection = res.result();
                sqlConnection.execute("insert into user values("+user.getId()+",'"+user.getUsername()+"','"+user.getPassword()+"','"+user.getUuid()+"')",insert-> System.out.println("succeded insert?"));
            } else System.out.println("res not succeded");
        });
    }
}
