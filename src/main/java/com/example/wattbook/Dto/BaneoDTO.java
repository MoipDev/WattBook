package com.example.wattbook.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Data
@Getter
@Setter
public class BaneoDTO {
    private Long id;
    private Long usuarioId;
    private String motivoBan;
    private LocalDate fecha;
}
