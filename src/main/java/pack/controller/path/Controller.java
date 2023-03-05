package pack.controller.path;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.core.Response;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import jakarta.inject.Inject;

// import pack.controller.interceptor.IdRequired;

import pack.builder.Built;

import java.util.ArrayList;

import pack.model.api.dto.Product;
import pack.model.api.dto.User;
import pack.model.api.in.IBasketModel;
import pack.model.api.in.IUserModel;
import pack.model.api.in.IProductModel;
import pack.repository.product.EProduct;

@Path("/")
public class Controller {

  @Inject
  @Built
  IBasketModel MB;

  @Inject
  @Built
  IProductModel MP;

  @Inject
  @Built
  IUserModel MU;

  @DELETE
  @Path("/basket/deleting/{id}")
  public boolean deleteFromBasket(@PathParam("id") int id,  String login) {
    return MB.deleteProduct(id, login);
  }

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
  public String autorization(String userJSON) {
    Jsonb jsonb = JsonbBuilder.create();
    User user = jsonb.fromJson(userJSON, User.class);

    String jwt = MU.authorization(user.getEmail(), user.getPassword());
    return jwt;
  }
}