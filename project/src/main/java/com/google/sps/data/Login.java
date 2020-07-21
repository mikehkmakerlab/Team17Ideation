  
package com.google.sps.data;

public final class Login {

  private final String loggedIn;
  private final String login;
  private final String logout;

  public Login(String loggedIn, String login, String logout) {
    this.loggedIn = loggedIn;
    this.login = login;
    this.logout = logout;
  }
}