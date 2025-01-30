package mx.exchange.Api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Table(name = "billetera")
@Entity(name = "Billetera")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Billetera {

    @EmbeddedId
    private BilleteraId id;

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @MapsId("idCriptomoneda")
    @JoinColumn(name = "id_criptomoneda")
    private Criptomoneda criptomoneda;

    private Double cantidad;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

}
