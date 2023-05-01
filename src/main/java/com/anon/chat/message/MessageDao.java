package com.anon.chat.message;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class MessageDao {
    @Size(max = 128)
    private final String sender;
    @Size(max = 1000)
    private final String message;
}
