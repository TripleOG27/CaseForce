package com.detelin.caseforce.domain.models.view;

import com.detelin.caseforce.GlobalConstants;
import com.detelin.caseforce.domain.entities.Role;
import com.detelin.caseforce.domain.entities.enums.UserStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class UserAllViewModel {
    private String id;
    private String username;
    private String email;
    private UserStatus status;
    private LocalDate created;
    private Set<String> authorities;
    private String highestAuthority;
    private String imageUrl;

    public UserAllViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public String getHighestAuthority() {
        return highestAuthority;
    }

    public void setHighestAuthority() {
        if(authorities.contains(GlobalConstants.ROOT_ROLE))this.highestAuthority = "Root";
        else if(authorities.contains(GlobalConstants.DEVELOPER_ROLE))this.highestAuthority = "Developer";
        else if(authorities.contains(GlobalConstants.ACCOUNT_MANAGER_ROLE))this.highestAuthority = "Account Manager";
        else if(authorities.contains(GlobalConstants.PRIVILEGES_ROLE))this.highestAuthority = "Privilege team";
        else if(authorities.contains(GlobalConstants.TSM_ROLE))this.highestAuthority = "Technical Support Manager";
        else if(authorities.contains(GlobalConstants.TSE_ROLE))this.highestAuthority = "Technical Support Engineer";
        else this.highestAuthority = "Customer";
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
