package org.brainmaster.entity;

import java.util.Set;
import org.jboss.logging.Logger;
import io.quarkiverse.renarde.security.RenardeUser;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.inject.Inject;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "member")
@UserDefinition
public class Member extends PanacheEntity implements RenardeUser {

  @Inject
  Logger log;

  @Username
  private String username;

  @Password
  private String password;

  @Roles
  private Set<String> roles;
  
  public Member(){}

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

  public Set<String> getRoles() {
    return roles;
  }

  public void setRoles(Set<String> roles) {
    this.roles = roles;
  }

  @Override
  public Set<String> roles() {
    return roles;
  }

  @Override
  public String userId() {
    return username;
  }

  @Override
  public boolean registered() {
    log.info("registered method called");
    return true;
  }
  
}
