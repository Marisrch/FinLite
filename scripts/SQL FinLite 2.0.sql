CREATE DATABASE finlite;
USE finlite;

CREATE TABLE Empreendedor (
    id_empreendedor INT AUTO_INCREMENT PRIMARY KEY,
    nome_empreendedor VARCHAR(100) NOT NULL,
    cpf_empreendedor CHAR(11) UNIQUE,
    email_empreendedor VARCHAR(100),
    telefone_empreendedor VARCHAR(20)
);

CREATE TABLE Venda (
    id_venda INT AUTO_INCREMENT PRIMARY KEY,
    nome_cliente VARCHAR(100) NOT NULL,
    produto_peca VARCHAR(100) NOT NULL,
    valor_venda DECIMAL(10,2) NOT NULL,
    forma_pagamento VARCHAR(30) NOT NULL,
    status_venda VARCHAR(30) NOT NULL,
    data_entrega DATE NOT NULL,
    data_registro DATE NOT NULL,
    id_empreendedor INT NOT NULL,

    FOREIGN KEY (id_empreendedor)
        REFERENCES Empreendedor(id_empreendedor)
);

CREATE TABLE Material (
    id_material INT AUTO_INCREMENT PRIMARY KEY,
    nome_material VARCHAR(100) NOT NULL,
    marca_material VARCHAR(50),
    cor_material VARCHAR(50),
    categoria_material VARCHAR(50) NOT NULL,
    quantidade_estoque_material DECIMAL(10,2) NOT NULL DEFAULT 0,
    quantidade_minima_material DECIMAL(10,2) NOT NULL DEFAULT 5,
    unidade_medida_material VARCHAR(20) NOT NULL,
    custo_unitario_material DECIMAL(10,2),
    id_empreendedor INT NOT NULL,

    FOREIGN KEY (id_empreendedor)
        REFERENCES Empreendedor(id_empreendedor)
);

CREATE TABLE Lancamento_Financeiro (
    id_lancamento INT AUTO_INCREMENT PRIMARY KEY,
    tipo_lancamento VARCHAR(50) NOT NULL,
    data_lancamento DATE NOT NULL,
    descricao_lancamento VARCHAR(255) NOT NULL,
    categoria_lancamento VARCHAR(50) NOT NULL,
    quantidade_lancamento DECIMAL(10,2),
    custo_unitario_lancamento DECIMAL(10,2),
    valor_total_lancamento DECIMAL(10,2) NOT NULL,
    id_material INT,
    id_empreendedor INT NOT NULL,

    FOREIGN KEY (id_material)
        REFERENCES Material(id_material),

    FOREIGN KEY (id_empreendedor)
        REFERENCES Empreendedor(id_empreendedor)
);

CREATE TABLE Movimentacao_Estoque (
    id_movimentacao INT AUTO_INCREMENT PRIMARY KEY,
    id_material INT NOT NULL,
    tipo_movimentacao VARCHAR(30) NOT NULL,
    quantidade_movimentacao DECIMAL(10,2) NOT NULL,
    data_movimentacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    motivo_origem VARCHAR(100),
    id_lancamento INT UNIQUE,

    FOREIGN KEY (id_material)
        REFERENCES Material(id_material),

    FOREIGN KEY (id_lancamento)
        REFERENCES Lancamento_Financeiro(id_lancamento)
);

SELECT * FROM Empreendedor;
SELECT * FROM Venda;
SELECT * FROM Material;
SELECT * FROM Lancamento_Financeiro;
SELECT * FROM Movimentacao_Estoque;


