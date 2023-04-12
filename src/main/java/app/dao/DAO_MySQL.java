package app.dao;

import app.config.Env;

public class DAO_MySQL {

  protected String dbUrl;
  protected String username;
  protected String password;
  protected Env env = new Env();
  public DAO_MySQL() {
    dbUrl = env.DBURL;
    username = env.USERNAME;
    password = env.PASSWORD;
  }

}
