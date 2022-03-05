package com.example.rentme_backend_morgan.security.controller;

import com.example.rentme_backend_morgan.security.dto.AccountDto;
import com.example.rentme_backend_morgan.security.dto.RegisterDto;
import com.example.rentme_backend_morgan.security.mapper.SecurityMapper;
import com.example.rentme_backend_morgan.security.services.ISecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

import static com.example.rentme_backend_morgan.security.api.SecurityApi.*;

@RestController
@RequestMapping(SECURITY)
@RequiredArgsConstructor
public class SecurityController {

    private final ISecurityService service;

    @PostMapping(ADD_ACCOUNT) //TODO method for add admin and manager in local area
    public AccountDto addAccount(@Valid @RequestBody RegisterDto registerDto) {

        return SecurityMapper.accountDto(
                service.addAccount(registerDto));
    }

    @PostMapping(ADD_USER)
    public AccountDto addUser(@Valid @RequestBody RegisterDto registerDto) {
        return SecurityMapper.accountDto(service.addProfile(registerDto));
    }

//    @PostMapping(ADD_OWNER)
//    public AccountDto addOwner(@Valid @RequestBody RegisterDto registerDto) {
//        return SecurityMapper.accountDto(service.addOwner(registerDto));
//    }

    @DeleteMapping(REMOVE_ACCOUNT)
    public AccountDto removeAccount(@Valid @RequestBody RegisterDto registerDto) {

        return SecurityMapper.accountDto(service.removeAccount(registerDto.getLogin()));
    }

    @DeleteMapping(REMOVE_USER)
    public AccountDto removeUser(String login) {
        return SecurityMapper.accountDto(service.removeUser(login));
    }

    @DeleteMapping(REMOVE_OWNER)
    public AccountDto removeOwner(String login) {
        return SecurityMapper.accountDto(service.removeOwner(login));
    }

    @PostMapping(REVOKE_ACCOUNT)
    public String revokeAccount(@Valid @RequestBody RegisterDto registerDto) {
//        return SecurityMapper.accountDto(service.revokeAccount(registerDto.getLogin()));
        return service.revokeAccount(registerDto.getLogin());
    }

    @PostMapping(ACTIVATE_ACCOUNT)
    public String activateAccount(@Valid @RequestBody RegisterDto registerDto) {
//        return SecurityMapper.accountDto(service.activateAccount(registerDto.getLogin()));
        return service.activateAccount(registerDto.getLogin());
    }

    @GetMapping(GET_ALL_ACCOUNTS)
    public List<AccountDto> getAllAccounts() {

        return SecurityMapper.accountDtoList(service.getAllAccounts());
    }

    @PutMapping(GRANT_ROLE)
    public AccountDto grantRole(@Valid @RequestBody RegisterDto registerDto) {

        return SecurityMapper.accountDto(service.grantRole(
                registerDto.getLogin(),
                registerDto.getRole()));
    }

//    @PutMapping(DEPRIVE_ROLE)
//    public AccountDto depriveRole(@Valid @RequestBody RegisterDto registerDto) {
//
//        return SecurityMapper.accountDto(service.depriveRole(
//                registerDto.getLogin(),
//                registerDto.getRole()));
//    }

    @GetMapping(GET_ROLE_BY_LOGIN)
    public String getRoleByLogin(@Valid @RequestBody RegisterDto registerDto) {
        return service.getRolesByLogin(registerDto.getLogin());
    }

    @PostMapping(CHANGE_PASSWORD)
    public AccountDto changePassword(@Valid @RequestBody RegisterDto registerDto) {

        return null;

//                SecurityMapper.accountDto(service.changePassword(
//                registerDto.getLogin(),
//                registerDto.getPassword()));
    }
}