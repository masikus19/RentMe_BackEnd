package com.example.rentme_backend_morgan.security.services;


import com.example.rentme_backend_morgan.security.dto.*;
import com.example.rentme_backend_morgan.security.entities.*;

import java.util.List;
import java.util.Set;

public interface ISecurityService {

    Account addProfile(RegisterDto registerDto); //TODO renter add for Renter Owner

//    Account addOwner(RegisterDto registerDto);

    Account addAccount(RegisterDto registerDto);

    Account grantRole(String login, String role);

//    Account depriveRole(String login, String role);

    String getRolesByLogin(String login);

    List<Account> getAllAccounts();

    Account removeAccount(String login);

    Account removeUser(String login);

    Account removeOwner(String login);

    String changePassword(String login, String password);

    String revokeAccount(String login);

    String activateAccount(String login);

}