package org.brainmaster.service;

import org.brainmaster.entity.Member;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AuthenticationService {

  @Transactional
  public void createAuthentication(Member member) {
    member.setPassword(BcryptUtil.bcryptHash(member.getPassword()));
    member.persist();
  }

}
