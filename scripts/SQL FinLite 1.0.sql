CREATE DATABASE finlite;
USE finlite;

CREATE TABLE Cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nome_cliente VARCHAR(100) NOT NULL,
    telefone_cliente VARCHAR(20),
    cpf_cliente CHAR(11) UNIQUE
);
CREATE TABLE Empreendedor (
    id_empreendedor INT AUTO_INCREMENT PRIMARY KEY,
    nome_empreendedor VARCHAR(100) NOT NULL,
    cpf_empreendedor CHAR(11) UNIQUE,
    email_empreendedor VARCHAR(100),
    telefone_empreendedor VARCHAR(20)
);
CREATE TABLE Produto (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,
    nome_produto VARCHAR(100) NOT NULL,
    descricao_produto VARCHAR(255),
    preco_base_produto DECIMAL(10,2) NOT NULL,
    prazo_producao_produto DATE,
    id_empreendedor INT NOT NULL,
    CONSTRAINT fk_produto_empreendedor
        FOREIGN KEY (id_empreendedor)
        REFERENCES Empreendedor(id_empreendedor)
);
CREATE TABLE Material (
    id_material INT AUTO_INCREMENT PRIMARY KEY,
    nome_material VARCHAR(100) NOT NULL,
    cor_material VARCHAR(50),
    marca_material VARCHAR(50),
    custo_unitario_material DECIMAL(10,2),
    quantidade_estoque_material DECIMAL(10,2),
    unidade_medida_material VARCHAR(20)
);
CREATE TABLE Venda (
    id_venda INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_empreendedor INT NOT NULL,
    data_venda DATE NOT NULL,
    status_venda VARCHAR(20),
    CONSTRAINT fk_venda_cliente
        FOREIGN KEY (id_cliente)
        REFERENCES Cliente(id_cliente),
    CONSTRAINT fk_venda_empreendedor
        FOREIGN KEY (id_empreendedor)
        REFERENCES Empreendedor(id_empreendedor)
);
CREATE TABLE Produto_Material (

    id_produto_material INT AUTO_INCREMENT PRIMARY KEY,
    id_produto INT NOT NULL,
    id_material INT NOT NULL,
    quantidade_material DECIMAL(10,2) NOT NULL,

    CONSTRAINT fk_pm_produto
        FOREIGN KEY (id_produto)
        REFERENCES Produto(id_produto)
        ON DELETE CASCADE,

    CONSTRAINT fk_pm_material
        FOREIGN KEY (id_material)
        REFERENCES Material(id_material)
        ON DELETE CASCADE
);
CREATE TABLE Item_Venda (
    id_item_venda INT AUTO_INCREMENT PRIMARY KEY,
    id_venda INT NOT NULL,
    id_produto INT NOT NULL,
    quantidade_item_venda INT NOT NULL,
    preco_unitario_item_venda DECIMAL(10,2) NOT NULL,
    custo_item_venda DECIMAL(10,2),
    CONSTRAINT fk_item_venda_venda
        FOREIGN KEY (id_venda)
        REFERENCES Venda(id_venda)
        ON DELETE CASCADE,
    CONSTRAINT fk_item_venda_produto
        FOREIGN KEY (id_produto)
        REFERENCES Produto(id_produto),
    CONSTRAINT uk_venda_produto
        UNIQUE (id_venda, id_produto)
);
CREATE TABLE Movimentacao_Estoque (
    id_movimentacao INT AUTO_INCREMENT PRIMARY KEY,
    id_material INT NOT NULL,
    tipo_movimentacao ENUM('entrada', 'saida') NOT NULL,
    quantidade DECIMAL(10,2) NOT NULL,
    data_movimentacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_mov_material
        FOREIGN KEY (id_material)
        REFERENCES Material(id_material)
);
INSERT INTO Empreendedor (
    nome_empreendedor,
    cpf_empreendedor,
    email_empreendedor,
    telefone_empreendedor
)
VALUES (
    'Ana',
    '12345678900',
    'ana@email.com',
    '11999999999'
);
 
SELECT * FROM Empreendedor;
SELECT * FROM Produto;
SELECT * FROM Cliente;
SELECT * FROM Material;
SELECT * FROM Venda;
SELECT * FROM Item_Venda;
SELECT * FROM Produto_Material;
SELECT * FROM Movimentacao_Estoque;

 
