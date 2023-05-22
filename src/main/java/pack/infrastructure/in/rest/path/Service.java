package pack.infrastructure.in.rest.path;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import jakarta.inject.Inject;

import java.util.ArrayList;

import pack.infrastructure.out.websocket.WebSocket;
import pack.application.api.out.IDeliveryUpdate;
import pack.application.implemetation.User;
import pack.application.dto.Delivery;
import pack.application.dto.Product;
import pack.application.api.in.IBasket;
import pack.application.api.in.IDelivery;
import pack.application.api.in.IUser;
import pack.application.api.in.IProduct;
import pack.infrastructure.out.repository.product.EProduct;
import pack.infrastructure.builder.Built;

@Path("/")
public class Service {
   @Inject @Built
   IBasket MB;

  @Inject
  @Built
  IProduct MP;

  @Inject
  @Built
  IUser MU;
  @Inject
  @Built
  IDelivery MD;

  @DELETE
  @Path("/products/deleting/{id}")
  public boolean deleteProduct(@PathParam("id") int id) { return
    MP.deleteProduct(id);
  }

  @PUT
  @Path("/basket/adding/{id}")
  @Consumes("text/plain")
  @Produces("text/plain")
  public boolean add(@PathParam("id") int id,  String login) {
    EProduct p = MP.findProductByID(id);
    return MB.addToBasketFromProducts(p, login);
  }

  @POST
  @Path("/basket")
  @Consumes("text/plain")
  @Produces("application/json")
  public Response tableBasket(String login) {
      ArrayList<Product> products = MB.findProductsBasket(login);
      Jsonb jsonb = JsonbBuilder.create();
      String productsJSON = jsonb.toJson(products);
      return Response.ok(productsJSON).build();
  }
  @POST
  @Path("/products/adding")
  @Consumes("application/json")
  @Produces("text/plain")
  public boolean addProduct(String productJSON)  {
    Jsonb jsonb = JsonbBuilder.create();
    Product p = jsonb.fromJson(productJSON, Product.class);
    return MP.addProduct(p);
  }
  @PUT
  @Path("/basket/deleting/{id}")
  @Consumes("text/plain")
  @Produces("text/plain")
  public boolean deleteFromBasket(@PathParam("id") int id,  String login) {
    return MB.deleteProduct(id, login);
  }

  @POST
  @Path("/products")
  @Consumes("text/plain")
  @Produces("application/json")
  public Response tableProducts(String token) {
    if (MU.authentication(token)) {
      ArrayList<Product> products = MP.findAllProducts();
      Jsonb jsonb = JsonbBuilder.create();
      String productsJSON = jsonb.toJson(products);
      return Response.ok(productsJSON).build();
    } else
      return Response.ok("invalid token").build();
  }

  @POST
  @Path("/login")
  @Consumes("application/json")
  @Produces("text/plain")
  public Response autorization(String userJSON) {
    Jsonb jsonb = JsonbBuilder.create();
    pack.application.dto.User user = jsonb.fromJson(userJSON, pack.application.dto.User.class);
    User.answer JWTAndRole=MU.authorization(user.getEmail(), user.getPassword());
    String j =jsonb.toJson(JWTAndRole);
    return Response.ok(j).build();
  }

  @POST
  @Path("user/orders")
  public Response getOrdersUser(String user){
    ArrayList<Delivery> d = MD.findOrderByLogin(user);
    Jsonb jsonb = JsonbBuilder.create();
    String productsJSON = jsonb.toJson(d);
    return Response.ok(productsJSON).build();
  }
  @GET
  @Path("admin/orders")
  public Response getOrders(){
    ArrayList<Delivery> d = MD.findAllOrders();
    Jsonb jsonb = JsonbBuilder.create();
    String productsJSON = jsonb.toJson(d);
    return Response.ok(productsJSON).build();
  }

  @POST
  @Path("/admin/delivery")
    public Response deliveryUpdateAsyncChangeStatus(String login){
    IDeliveryUpdate updater;
    updater = () -> {
      WebSocket.send(login);
    };

    MD.update(updater,login);
    String res = "Customer data is updated";

      return Response
              .ok(res)
              .build();
    }

  }

