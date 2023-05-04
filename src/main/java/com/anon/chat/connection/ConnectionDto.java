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
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "connection")
public class ConnectionDto {

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "connection_seq")
    @Id
    @Column(name = "c_id")
    private Long id;

    @Column(name = "c_created_date")
    private LocalDateTime createdDate;

    @Column(name = "c_active")
    private boolean active;
}
