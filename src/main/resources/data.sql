-- Insertar productos
INSERT INTO producto (cod_producto, nombre_producto, ubicacion) VALUES
('P001', 'Martillo', 'Estante 1'),
('P002', 'Destornillador', 'Estante 2'),
('P003', 'Taladro', 'Estante 3'),
('P004', 'Sierra', 'Estante 4'),
('P005', 'Pico', 'Estante 5');

-- Insertar ventas
INSERT INTO Venta (ticket_Code, precio, fecha) VALUES
('TXS8D31', 350.00, '2025-01-06'),
('TTDIC18', 1150.00, '2025-01-06');


INSERT INTO VENTA_PRODUCTO (PRODUCTO_COD, VENTA_ID) VALUES
(1, 1),
(1, 2),
(2, 2);