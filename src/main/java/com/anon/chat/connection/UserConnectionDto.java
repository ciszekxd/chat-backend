package com.anon.chat.connection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_connection")
public class UserConnectionDto {

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "connection_seq")
    @Id
    @Column(name = "uc_id")
    private Long id;

    @Column(name = "uc_connection_id")
    private Long connectionId;

    @Column(name = "uc_user_id")
    private Long userId;

}
