-- CREATE DATABASE db_sprintquiz_h2;
-- USE db_sprintquiz_h2;


-- criando usuario
--        Usuario usuario1 = new Usuario(null, "Jonathan", "jonathan@email.com", "12345678", "", 0, permissions, roles, new ArrayList<Questao>(), new ArrayList<Prova>());
--INSERT INTO tb_usuario(foto, nome, senha, usuario) VALUES ('foto', 'jonathan','$2a$10$FBC36RE9lGsHbYiQfsEjZOe5eVyXOpkdKoelfBL2kcT8eb.iNS78m', 'jonathan@email.com');
INSERT INTO tb_usuario( nome, username, password,foto) VALUES ('frodo','frodo@email.com','$2a$10$FBC36RE9lGsHbYiQfsEjZOe5eVyXOpkdKoelfBL2kcT8eb.iNS78m','foto');

--criando os cargos
INSERT INTO tb_role(cargo) VALUES('ROLE_ADMIN');
INSERT INTO tb_role(cargo) VALUES('ROLE_GERENTE');
INSERT INTO tb_role(cargo) VALUES('ROLE_USER');

----criando associacao entre usuario e cargo
--INSERT INTO tb_usuario VALUES(1, 1);

-- criando categoria de prova
INSERT INTO tb_categoria_prova(titulo, descricao) VALUES('Vestibulares publicos', 'Provas de Vestibulares publicos');
INSERT INTO tb_categoria_prova(titulo, descricao) VALUES('Vestibulares Particulares', 'Provas de Vestibulares Particulares');

-- criando prova
INSERT INTO tb_prova(nome, descricao, instituicao, duracao, categoria_id, usuario_id) VALUES('Fuvest','Vestibular da fuvest 2019','Usp', 6, 1, 1);
INSERT INTO tb_prova(nome, descricao, instituicao, duracao, categoria_id, usuario_id) VALUES('UFRJ Vestibular','Vestibular da UFRJ 2021','UFRJ', 6, 2, 1);

-- criando categoria de questao
INSERT INTO tb_categoria_questao(titulo, descricao) VALUES('facil', 'questao de nivel facil');
INSERT INTO tb_categoria_questao(titulo, descricao) VALUES('Medio', 'questao de nivel medio');
INSERT INTO tb_categoria_questao(titulo, descricao) VALUES('Dificio', 'questao de nivel dificil');

-- criando questao
INSERT INTO tb_questao(texto, imagem, instituicao,ano, dificuldade,categoria_id, criador_id) VALUES('Qual a cor da neve?','https://i.imgur.com/r98IEMu.png', 'Etec', curdate(),1, 1, 1);
INSERT INTO tb_questao(texto, imagem, instituicao,ano,  dificuldade,categoria_id, criador_id) VALUES('Qual a cor da Laranja?','https://i.imgur.com/r98IEMu.png', 'Usp', curdate(),1, 1, 1);
INSERT INTO tb_questao(texto, imagem, instituicao,ano,  dificuldade,categoria_id, criador_id) VALUES('Qual a cor do Limão?','https://i.imgur.com/r98IEMu.png', 'UFRJ', curdate(),1, 1, 1);



-- criando associacao entre prova e questao
INSERT INTO tb_questao_prova(prova_id, questao_id) VALUES (1, 1);
INSERT INTO tb_questao_prova(prova_id, questao_id) VALUES (1, 2);
INSERT INTO tb_questao_prova(prova_id, questao_id) VALUES (1, 3);

INSERT INTO tb_questao_prova(prova_id, questao_id) VALUES (2, 1);
INSERT INTO tb_questao_prova(prova_id, questao_id) VALUES (2, 2);
INSERT INTO tb_questao_prova(prova_id, questao_id) VALUES (2, 3);

-- criando alternativas (com id da questao)
--INSERT INTO tb_alternativa(texto, foto, questao_id) VALUES('Verde', '', 1);
--INSERT INTO tb_alternativa(texto, foto, questao_id) VALUES('Branco', '', 1);
--INSERT INTO tb_alternativa(texto, foto, questao_id) VALUES('Laranja', '', 1);
--
--INSERT INTO tb_alternativa(texto, foto, questao_id) VALUES('Preto', '', 2);
--INSERT INTO tb_alternativa(texto, foto, questao_id) VALUES('Rosa', '', 2);
--INSERT INTO tb_alternativa(texto, foto, questao_id) VALUES('Laranja', '', 2);
--
--INSERT INTO tb_alternativa(texto, foto, questao_id) VALUES('Verde', '', 3);
--INSERT INTO tb_alternativa(texto, foto, questao_id) VALUES('Cinza', '', 3);
--INSERT INTO tb_alternativa(texto, foto, questao_id) VALUES('Roxo', '', 3);

-- criando alternativas (sem id da questao)
INSERT INTO tb_alternativa(texto, foto) VALUES('Verde', '');
INSERT INTO tb_alternativa(texto, foto) VALUES('Branco', '');
INSERT INTO tb_alternativa(texto, foto) VALUES('Laranja', '');

INSERT INTO tb_alternativa(texto, foto) VALUES('Preto', '');
INSERT INTO tb_alternativa(texto, foto) VALUES('Rosa', '');
INSERT INTO tb_alternativa(texto, foto) VALUES('Laranja', '');

INSERT INTO tb_alternativa(texto, foto) VALUES('Verde', '');
INSERT INTO tb_alternativa(texto, foto) VALUES('Cinza', '');
INSERT INTO tb_alternativa(texto, foto) VALUES('Roxo', '');



-- atualizando respostas das questoes
--UPDATE tb_questao SET resposta_id = 1 WHERE (id = 1);
--UPDATE tb_questao SET resposta_id = 4 WHERE (id = 2);
--UPDATE tb_questao SET resposta_id = 7 WHERE (id = 3);

-- atualizando lista de alternativas das questoes
--UPDATE tb_alternativa SET questao_id = 1 WHERE (id = 1);
--UPDATE tb_alternativa SET questao_id = 1 WHERE (id = 2);
--UPDATE tb_alternativa SET questao_id = 1 WHERE (id = 3);
--
--UPDATE tb_alternativa SET questao_id = 2 WHERE (id = 4);
--UPDATE tb_alternativa SET questao_id = 2 WHERE (id = 5);
--UPDATE tb_alternativa SET questao_id = 2 WHERE (id = 6);
----
--UPDATE tb_alternativa SET questao_id = 3 WHERE (id = 7);
--UPDATE tb_alternativa SET questao_id = 3 WHERE (id = 8);
--UPDATE tb_alternativa SET questao_id = 3 WHERE (id = 9);

--UPDATE tb_alternativa SET questao_id = 1 WHERE (id = 4);
--UPDATE tb_alternativa SET questao_id = 2 WHERE (id = 7);
--UPDATE tb_alternativa SET questao_id = 3 WHERE (id = 3);


-- criando associacao entre questao e alternativa
--INSERT INTO tb_questao(alternativa_id) WHERE (id = 1) VALUES (1);
--INSERT INTO tb_questao(alternativa_id) WHERE (id = 1) VALUES (2);
--INSERT INTO tb_questao(alternativa_id) WHERE (id = 1) VALUES (3);
--
--INSERT INTO tb_questao(alternativa_id) WHERE (id = 2) VALUES (4);
--INSERT INTO tb_questao(alternativa_id) WHERE (id = 2) VALUES (5);
--INSERT INTO tb_questao(alternativa_id) WHERE (id = 2) VALUES (6);
