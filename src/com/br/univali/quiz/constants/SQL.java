package com.br.univali.quiz.constants;

/**
 * Classe estática que contêm todos os SQL da aplicação.
 * 
 * @author Jean Carlos da Silva Ferreira (jeansferreira@gmail.com)
 * @version 1.0
 * @exception Caso seja escolido algum SQL expresso aqui 
 * e não for para a estrutura correta poderá ocorrer um erro no
 * código a frente.
 */
public class SQL
{
	/**
	 * SQL retorna todos os dados da TABLE ESTRUTURA.
	 */
	public static String sqlFindEstruturas = "SELECT id, nome FROM assunto";
	
	/**
	 * SQL que verifica se o usuário existe na base de dados.
	 */
	public static String sqlFindUser = "SELECT perfil FROM login WHERE login = ? AND password = PASSWORD(?)";

	/**
	 * SQL que inseri um novo usuario no sistema.
	 */
	public static String sqlInsertUser = "INSERT INTO login (login, password) VALUES (?,PASSWORD(?))";
    
    /**
     * SQL que inseri um novo assunto no sistema.
     */
    public static String sqlInsertNovoAssunto = "INSERT INTO assunto (nome) VALUES (?)";

    /**
     * SQL que inseri uma nova pergunta no sistema.
     */
    public static String sqlInsertPergunta = "INSERT INTO pergunta (pergunta, relacao_pergunta, relacao_resposta) VALUES (?,?,?)";    
    
    /**
     * SQL que inseri uma resposta no sistema.
     */
    public static String sqlInsertResposta = "INSERT INTO resposta (resposta, relacao_pergunta, status) VALUES (?,?,?)";

    /**
     * SQL que retorna uma lista de perguntas por assunto
     */
    public static String sqlFindListPergunta = "SELECT perg.id, perg.pergunta, ass.nome  FROM pergunta perg, assunto ass WHERE perg.relacao_pergunta = ? AND perg.relacao_pergunta = ass.id";
}
