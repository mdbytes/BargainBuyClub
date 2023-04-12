package app.dao;

import app.model.Product;
import app.model.Store;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class ProductDAO_MySQL extends DAO_MySQL implements DAO<Product>{

  public ProductDAO_MySQL() {
    super();
  }

  @Override
  public Product add(String[] args) {
    DAO storeDao = new StoreDAO_MySQL();

    int storeId = Integer.parseInt(args[0]);
    String productUrl = args[1];

    if (getProductByURL(productUrl).getProductID() == 0) {
      Store store = (Store) storeDao.get(storeId);
      Product product = new Product(productUrl, store);
      String query = "INSERT INTO products "
          + "(store_id,product_url) "
          + "VALUES (" + storeId + ",'" + productUrl + "');";
      try {
        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        Statement statement = connection.createStatement();
        int count = statement.executeUpdate(query);

      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
      product = getProductByURL(productUrl);
      return product;
    } else {
      return getProductByURL(productUrl);
    }

     }

  @Override
  public Product get(int id) {
    Product product = new Product();
    DAO storeDao = new StoreDAO_MySQL();

    String query = "SELECT * FROM products "
        + "WHERE product_id = " + id + ";";
    try {
      ResultSet rs = null;
      try {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        Statement statement = connection.createStatement();
        rs = statement.executeQuery(query);

      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
      while (rs.next()) {
        product.setProductID(id);
        Store store = (Store) storeDao.get((int) rs.getObject("store_id"));
        product.setStore(store);
        product.setProductUrl(rs.getString("product_url"));
        try {
          product.setProductName(store.getProductName(rs.getString("product_url")));
          product.setProductPrice(store.getProductPrice(rs.getString("product_url")));
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
        break;
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return product;
  }

  public Product addProduct(int storeId, String productUrl) {
    String[] args = {Integer.toString(storeId),productUrl};
    return add(args);

  }

  @Override
  public Product update(Product product) {
    return null;
  }

  @Override
  public void delete(int id) {

  }

  @Override
  public List<Product> getAll() {
    return null;
  }

  public Product getProductByURL(String productURL) {
    Product product = new Product();
    DAO storeDao = new StoreDAO_MySQL();

    String query = "SELECT * FROM products "
        + "WHERE product_url = '" + productURL + "';";
    try {
      ResultSet rs = null;
      try {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        Statement statement = connection.createStatement();
        rs = statement.executeQuery(query);

      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
      while (rs.next()) {
        product.setProductID((int) rs.getObject("product_id"));
        Store store = (Store) storeDao.get((int) rs.getObject("store_id"));
        product.setStore(store);
        product.setProductUrl(productURL);
        try {
          product.setProductName(store.getProductName(rs.getString("product_url")));
          product.setProductPrice(store.getProductPrice(rs.getString("product_url")));
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
        break;
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return product;
  }
}
