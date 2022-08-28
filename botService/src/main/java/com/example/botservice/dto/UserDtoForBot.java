package com.example.botservice.dto;

import com.example.botservice.entity.User;
import lombok.*;

/**
 * @author "Nomozov Doniyor"
 * @created 12:32 on 23.08.2022
 * @project IntelliJ IDEA
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDtoForBot {
    private User user;
    private String language;
}
