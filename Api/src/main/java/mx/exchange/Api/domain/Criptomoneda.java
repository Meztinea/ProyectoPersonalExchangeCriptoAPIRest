package mx.exchange.Api.domain;

import jakarta.persistence.*;
import lombok.*;
import mx.exchange.Api.dto.ActualizarCriptomonedaDTO;
import mx.exchange.Api.dto.ActualizarPrecioCriptomonedaDTO;
import mx.exchange.Api.dto.CrearCriptomonedaDTO;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Locale;

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
        this.ticker = criptomonedaDTO.ticker().toUpperCase(Locale.ENGLISH);
        this.precioActual = criptomonedaDTO.precioActual();
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void actualizarTicker(ActualizarCriptomonedaDTO criptomonedaDTO) {
        this.ticker = criptomonedaDTO.ticker().toUpperCase(Locale.ENGLISH);
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void actualizarPrecio(ActualizarPrecioCriptomonedaDTO criptomonedaDTO){
        this.precioActual = criptomonedaDTO.payload().ultimoPrecio();
        this.fechaActualizacion = LocalDateTime.now();
    }
}
