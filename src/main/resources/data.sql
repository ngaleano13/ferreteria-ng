-- Insertar productos
INSERT INTO producto (cod_producto, nombre_producto, ubicacion) VALUES
('MT103', 'Martillo', 'Estante 1'),
('DES140', 'Destornillador', 'Estante 2'),
('TDRO10', 'Taladro', 'Estante 3'),
('SRRA65', 'Sierra', 'Estante 4'),
('CLA50', 'Clavos', 'Estante 5');

-- Insertar ventas
INSERT INTO Venta (ticket_Code, precio, fecha) VALUES
('TXS8D31', 5550.00, '2025-01-06'),
('TTDIC18', 1150.00, '2025-01-07');


INSERT INTO VENTA_PRODUCTO (PRODUCTO_COD, VENTA_ID) VALUES
(1, 1),
(2, 1),
(3, 1),
(1, 2),
(5, 2);


INSERT INTO usuario (username, contrasena) VALUES 
('admin', '$2a$12$4Q0gVKciK5NQHdfKuOMoreblaULX8fNf0ahkFJ79bvo.C6LFJvgTK'),
('user', '$2a$12$EqSCNiUptxrXLtBrVjO7R.Sa0VNCtLUzQA2ujURwGcBY0MqgOZW86'),
('nico', '$2a$12$MTu.8N/dexgjwAnSvxjFTOcxqar5xCRLjVx5wIJqS21RZb6YA7uG.')