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

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "connetion")
public class UserConnectionDto {

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "connection_seq")
    @Id
    @Column(name = "uc_id")
    private Long Id;

    @Column(name = "uc_user_hash")
    private String userName;

    @Column(name = "c_created_date")
    private LocalDateTime createdDate;

}
