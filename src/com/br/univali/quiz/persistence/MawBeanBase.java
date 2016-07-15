/*
 * Created on 31/03/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.br.univali.quiz.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author jean
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MawBeanBase
{
	protected Connection getConnection() throws NamingException, SQLException
	{
		/*Assim ta funcionando pro jboss*/
		   Context iniCtx = new InitialContext();
		   Context confCtx = (Context)iniCtx.lookup("java:");
		   DataSource ds = (DataSource)confCtx.lookup("StudyQuizMyDS");
		   return ds.getConnection();
		

		/*Assim pro tomcat
		InitialContext initialContext = new InitialContext();
		Context envContext = (Context) initialContext.lookup("java:comp/env");
		DataSource dataSource = (DataSource) envContext.lookup("jdbc/MawMyDS");
		return dataSource.getConnection();
		*/

	}

	/**
		* Liberar uma conexão do banco de dados,
		* ignorando excessões, uma vez que não ha
		* mais tempo
		*/
	protected void releaseConnection(Connection cn)
	{
		try
		{
			cn.close();
		}
		catch (Exception e)
		{
			/* Se estamos liberando a conex?o, n?o temos muito o
				que tratar na exce??o */
		}
	}

	/**
		* Liberar uma conexão do banco de dados
		* com um statement possivelmente aberto,
		* fechando o statement e a conexão
		*/
	protected void releaseConnection(Connection cn, Statement st)
	{
		try
		{
			st.close();
		}
		catch (Exception e)
		{
			/* Se estamos liberando a conex?o, n?o temos muito o
				que tratar na exce??o */
		}
		releaseConnection(cn);
	}

	/**
		* Tratamento padr?o para exce??es em m?todos que envolvem
		* bancos de dados, gerando um SQLException e carregando
		* a exce??o original com uma mensagem contextualizando
		* esta exce??o
		*/
	protected void tratarExcecaoBancoDeDados(Exception e) throws Exception
	{
		if (e instanceof NamingException)
		{
			e.printStackTrace();
			throw new Exception("Erro de JNDI ao conectar ao banco de dados", e);
		}
		else if (e instanceof SQLException)
		{
			e.printStackTrace();
		}
		else
		{
			//throw new Exception(e);
			e.printStackTrace();
			throw new Exception("Dados Invalidos: " + e.getMessage(), e);
		}
	}

	/**
		* @param st Statement inicializado para executar o Select
		* @param query Texto a ser utilizado como consulta sql
		* @return um ResultSet com os dados encontrados.
		*/
	protected ResultSet execSelect(Statement st, String query) throws SQLException
	{
		ResultSet res = st.executeQuery(query);
		return res;
	}

	/**
		* Executar um update ou delete no banco de dados.
		* @param query Instrução SQL para executar o update ou delete.
		*/
	protected void execSql(String query) throws Exception
	{
		Connection cn = null;
		Statement st = null;
		try
		{
			cn = getConnection();
			st = cn.createStatement();
			st.executeUpdate(query);
		}
		catch (Exception e)
		{
			tratarExcecaoBancoDeDados(e);
		}
		finally
		{
			releaseConnection(cn, st);
		}
	}

}