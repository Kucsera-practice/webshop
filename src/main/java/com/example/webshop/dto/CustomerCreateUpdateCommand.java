package com.example.webshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreateUpdateCommand {

    @NotBlank(message = "Ez nem jó, mer üres")
    @Size(min = 2, max = 255 , message = "Ez nem jó, mer rövid")
    private String name;

    @NotNull(message = "Nem lehetsz hajláéktalan")
    @Min(value = 1,message = "nem lehet 0")
    @Max(value = 10,message = "10 nél nagyobb id jó ház nincs")
    private Long addressId;
}
