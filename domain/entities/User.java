package com.detelin.caseforce.domain.entities;

import com.detelin.caseforce.domain.entities.enums.UserStatus;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
    private String username;
    private String password;
    private String email;
    private String imageUrl;
    private UserStatus status;
    private LocalDate created;
    private Set<Role> authorities;

    public User() {
    }

    @Override
    @Column(name = "username",nullable = false,unique = true,updatable = false)
    public String getUsername() {
        return username;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @Column(name = "passwd",nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "email",unique = true,nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    @ManyToMany(targetEntity = Role.class,fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
    @Column(name = "date_created")
    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }
    @PrePersist()
    void preInsertDefaultImage(){
        if(this.imageUrl==null)this.imageUrl="https://i2.wp.com/wimcanada.org/wp-content/uploads/2015/11/empty-profile.jpg";
    }
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
