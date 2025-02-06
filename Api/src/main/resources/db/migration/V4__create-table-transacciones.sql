CREATE TABLE transacciones (
    id_transaccion INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_criptomoneda INT NOT NULL,
    tipo ENUM('DEPOSITO', 'RETIRO', 'COMPRA', 'VENTA') NOT NULL,
    monto_MXN DECIMAL(18,8) NOT NULL,
    cantidad_criptomoneda DECIMAL(18,8) NOT NULL,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_criptomoneda) REFERENCES criptomonedas(id_criptomoneda) ON DELETE CASCADE
);