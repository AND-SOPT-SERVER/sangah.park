package org.sopt.diary.repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class UserEntity {
    public static final int MAX_INPUT_LENGTH = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", nullable = false)
    private Long id;

    @Column(name="name", nullable = false, length = 10)
//    @Size(max = MAX_INPUT_LENGTH, message = "최대 10글자까지 입력 가능합니다.")
    private String name;

//    @Size(max = MAX_INPUT_LENGTH, message = "최대 10글자까지 입력 가능합니다.")
    @Column(name="nickname", nullable = false, length = 10)
    private String nickname;

//    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    public UserEntity(String name, String nickname, String email, String password) {
        this.name=name;
        this.nickname=nickname;
        this.email=email;
        this.password=password;
    }

    public UserEntity(){
    }

    public Long getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public String getNickname(){
        return nickname;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }
}
