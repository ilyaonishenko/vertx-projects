package controller;

import DAO.UserDaoImpl;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import models.User;

/**
 * Created by woqpw on 23.09.15.
 */
public class Application extends AbstractVerticle {
    private void register(RoutingContext routingContext){
        JsonObject json = routingContext.getBodyAsJson();
        System.out.println(json);
        if (json == null) {
            routingContext.response().setStatusCode(400).end();
            System.out.println("problems");
        } else {
            User user = new User(json.getString("name"),json.getString("password"),json.getString("uuid"));
            System.out.println("username = " + user.getUsername() + " password = " + user.getPassword()+" uuid = "+user.getUuid());
            JsonObject config = new JsonObject()
                    .put("url","jdbc:mysql://localhost:3306/vertxTest")
                    .put("driver_class", "com.mysql.jdbc.Driver")
                    .put("user","root")
                    .put("password", "newpassword");
            JDBCClient client = JDBCClient.createShared(vertx, config, "vertxTest");
            UserDaoImpl userDao = new UserDaoImpl(client);
            userDao.addUser(user);
            /*client.getConnection(res->{
                if(res.succeeded()){
                    SQLConnection sqlConnection = res.result();
//                    sqlConnection.queryWithParams(query,params,res2->{
                    sqlConnection.execute("insert into user values("+user.getId()+",'"+user.getUsername()+"','"+user.getPassword()+"','"+user.getUuid()+"')",insert-> System.out.println("succeded insert?"));
                } else {
                    System.out.println("res not succeded");
                }
            });*/
        }
    }
    private void echo(RoutingContext routingContext){
        routingContext.response()
                .putHeader("content-type","application/json; charset=utf-8")
                .end(Json.encodePrettily("hello"));
    }
    @Override
    public void start(Future<Void> fut){
        Router router = Router.router(vertx);
        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response
                    .putHeader("content-type","text/html")
                    .end("<h1>It's main page.<h1><br><a href=\"/assets/registration.html\">link to registration</a>");
        });
        router.route("/assets/*").handler(StaticHandler.create("assets"));
        router.route("/api/user/newuser*").handler(BodyHandler.create());
        router.post("/api/user/newuser/:username").handler(this::register);
        router.get("/api/echo").handler(this::echo);
        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        config().getInteger("http.port", 8080),
                        result -> {
                            if (result.succeeded()) {
                                fut.complete();
                            } else {
                                fut.fail(result.cause());
                            }
                        }
                );
    }
}
