package com.bobocode.socket.jackson;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String firstName;
    private String lastName;
    private String city;
    private String email;
}
