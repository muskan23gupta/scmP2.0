//1097753970638-has0j2kuo08p1ek0v253no0v9msg6gnf.apps.googleusercontent.com
//client id and client secret 
//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

package com.scm.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

//we got redmark in User because we haveto implements un implementated methods to fullfil contract between abstract and interface just clicking on @Builder we get option add unimplemented methods

public class User implements UserDetails{
    @Id
    private String userId;
    @Column(name =  "user_name", nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @Column(length = 1000)
    private String about;
    private String profilePic;
    private String phoneNumber;

    //this method is used the generation of getter method of a paticular field
    //@Getter(value = AccessLevel.NONE)
    private boolean enabled = true;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;
    
   // private Providers provider = Providers.SELF;
   @Enumerated(EnumType.STRING)
 private Providers provider = Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();    

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();

    //Implementing methods from UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> roles = roleList.stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
      return roles; // If you have roles, thta which user have which rolesand authority .
    }


    //for this project email id is our username so we are returning email as a username
    @Override
    public String getUsername() {
        return this.email; // Spring Security uses username for authentication, returning email here.
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Return false if account expiry logic is implemented.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Return false if account locking logic is implemented.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Return false if credentials expiry logic is implemented.
    }

    @Override
    public boolean isEnabled() {
        return this.enabled; // Uses the `enabled` field to check account activation.
    }

    @Override
    public String getPassword() {
        return this.password; // Uses the `enabled` field to check account activation.
    }
}
