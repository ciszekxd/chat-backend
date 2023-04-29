package com.anon.chat.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat_user")
public class ChatUserDto {

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "chat_user_cu_id_seq")
    @Column(name = "cu_id")
    @NotNull
    @Id
    private Long id;

    @Column(name = "cu_user_hash")
    @NotNull
    @Size(max = 50)
    private String userHash;

    @Column(name = "cu_created_date")
    @NotNull
    private LocalDateTime createdDate;

    public ChatUserDto(String userHash, LocalDateTime createdDate) {
        this.userHash = userHash;
        this.createdDate = createdDate;
    }
}
