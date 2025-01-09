package mx.exchange.Api.domain;

import jakarta.persistence.*;
import lombok.*;
import mx.exchange.Api.dto.CrearCriptomonedaDTO;

import java.time.LocalDateTime;

@Table(name = "criptomonedas")
@Entity(name = "Criptomoneda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Criptomoneda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_criptomoneda")
    private Long id;

    private String ticker;

    @Column(name = "precio_actual")
    private Double precioActual;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    public Criptomoneda(CrearCriptomonedaDTO criptomonedaDTO){
        this.ticker = criptomonedaDTO.ticker();
        this.precioActual = criptomonedaDTO.precioActual();
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

}
