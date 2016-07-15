package com.br.univali.quiz.persistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.br.univali.quiz.constants.SQL;
import com.br.univali.quiz.dao.EstruturaDAO;
import com.br.univali.quiz.dao.PerguntaDAO;
import com.br.univali.quiz.dao.RespostaDAO;
import com.br.univali.quiz.utils.Data;

/**
 * Classe que faz requisições a base de dados solicitadas pela aplicação, e 
 * manipula estes dados vindos da base, retornando as classes que manipulam
 * as mesmas. 
 * 
 * @author Jean Carlos da Silva Ferreira (jeansferreira@gmail.com)
 * @version 1.0
 * @exception Caso não haja dados na base gera um erro apresentado 
 * no LOG do JBOSS.
 */
public class PersistenceDados extends MawBeanBase implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean inserirUsuario(String cpf, String nome, String login,
			String senha, String id_fornecedor, String email) {
		Connection cn = null;
		Statement st = null;
		boolean retorno = false;

		try {
			cn = getConnection();
			st = cn.createStatement();
			String query = "INSERT INTO usuario_fornecedor (cpf, nome, login, senha, cod_fornecedor, email) VALUES";
			query += "( '" + cpf + "' , '" + nome + "' , '" + login + "' , '"
					+ senha + "' , " + id_fornecedor + " , '" + email + "' );";
			this.execSql(query);
			retorno = true;
		} catch (Exception e) {
			System.out
					.println("Sessao verificaUsuario -> Problemas na busca .");
		} finally {
			this.releaseConnection(cn, st);
		}
		return retorno;
	}

	/**
	 * Metodo que busca todas as estruturas da BASE DE DADOS na tabela
	 * ESTRUTURA.
	 * 
	 * @return lista com as estrutura.
	 */
	public ArrayList findAssunto() {
		Connection cn = null;
		Statement st = null;
		ArrayList retorno = new ArrayList();

		try {
			cn = getConnection();
			st = cn.createStatement();

			String query = SQL.sqlFindEstruturas;
			ResultSet rs = this.execSelect(st, query);

			while (rs.next()) {
				EstruturaDAO est = new EstruturaDAO();

				est.setId(rs.getString(1));
				est.setNome(rs.getString(2));
				retorno.add(est);
			}
		} catch (Exception e) {
			System.out.println("Erro : " + e.getMessage());
		} finally {
			this.releaseConnection(cn, st);
		}
		return retorno;
	}
	
	/**
	 * Metodo que verifica se os dados do login na BASE DE DADOS na tabela LOGIN.
	 * 
	 * @return true se existe e false se não existe.
	 * @throws Exception 
	 */
	public boolean findUser(String login, String password) throws Exception {
		Connection cn = null;
		PreparedStatement pStm = null;
		
		try {
			cn = getConnection();
			pStm = cn.prepareStatement(SQL.sqlFindUser);
			pStm.setString(1, login);
			pStm.setString(2, password);
			ResultSet rs = pStm.executeQuery();
			
			if (rs.next())
			{
				return true;
			}
		} catch (Exception e)
		{
			throw new Exception("Dados invalidos.\n"+ e.getMessage());
		} finally {
			this.releaseConnection(cn);
		}
		return false;
	}
	
	/**
	 * Metodo que inseri um novo usuário na BASE DE DADOS na tabela login.
	 * 
	 * @return false se não inseriu e true se inseriu.
	 */
	public boolean insertUser(String login, String password) {
		Connection cn = null;
		PreparedStatement pStm = null;
		
		try {
			cn = getConnection();
			pStm = cn.prepareStatement(SQL.sqlInsertUser);
			pStm.setString(1, login);
			pStm.setString(2,password);
			System.out.println("Sql : "+SQL.sqlInsertUser);
			pStm.execute();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("Erro : " +e.getMessage());
		} finally {
			this.releaseConnection(cn);
		}
		return false;
	}
    
    /**
     * Metodo que inseri um novo assunto na BASE DE DADOS na tabela assunto.
     * 
     * @return false se não inseriu e true se inseriu.
     */
    public boolean insertNovoAssunto(String assunto) {
        Connection cn = null;
        PreparedStatement pStm = null;
        
        try {
            cn = getConnection();
            pStm = cn.prepareStatement(SQL.sqlInsertNovoAssunto);
            pStm.setString(1, assunto);
            System.out.println("Sql : "+SQL.sqlInsertNovoAssunto);
            pStm.execute();
            
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro : " +e.getMessage());
        } finally {
            this.releaseConnection(cn);
        }
        return false;
    }
    
    /**
     * Metodo que inseri uma pergunta com suas devidas respostas.
     * 
     * @return false se não inseriu e true se inseriu.
     */
    public boolean insertQuiz( String assunto, String pergunta, ArrayList respostas, String status ) {
        Connection cn = null;
        PreparedStatement pStm = null;
        
        long hash = Data.getDataAtualSegundos1998();
        
        try {
            cn = getConnection();
            pStm = cn.prepareStatement(SQL.sqlInsertPergunta);
            pStm.setString( 1, pergunta                             );
            pStm.setString( 2, assunto                              );
            pStm.setString( 3, (String.valueOf( hash )+"_"+assunto) );
            
            pStm.execute();
            System.out.println("Sql : "+SQL.sqlInsertPergunta);
            
            for( int i = 0 ; i < respostas.size() ; i++ )
            {
               RespostaDAO res = new RespostaDAO();
               res = (RespostaDAO) respostas.get(i);

               pStm = cn.prepareStatement(SQL.sqlInsertResposta);
               
               pStm.setString( 1, res.getResposta()                    );
               pStm.setString( 2, (String.valueOf( hash )+"_"+assunto) );
               pStm.setString( 3, status );
               
               pStm.execute();
               System.out.println("Sql : "+SQL.sqlInsertResposta);
            }
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro : " +e.getMessage());
        } finally {
            this.releaseConnection(cn);
        }
        return false;
    }    

    /**
     * Metodo que retorna todas pergutnas referentes ao assunto desejado.
     * 
     * @return true se existe e false se não existe.
     */
    public ArrayList findListQuiz(ArrayList assuntos) {
        Connection cn = null;
        PreparedStatement pStm = null;
        ArrayList ret = new ArrayList();
        ArrayList pergunta = new ArrayList();
        
        try {
            cn = getConnection();
            pStm = cn.prepareStatement(SQL.sqlFindListPergunta);
            
            for( int i = 0 ; i < assuntos.size() ; i++ )
            {
               pStm.setString(1, assuntos.get(i).toString());
               ResultSet rs = pStm.executeQuery();
               System.out.println("Sql : "+SQL.sqlFindListPergunta);
               int count = 0;
               pergunta  = new ArrayList();
               while (rs.next())
               {
                  PerguntaDAO perg = new PerguntaDAO();
                  
                  perg.setCount(String.valueOf( count ) );
                  perg.setId(rs.getString(1));
                  perg.setPergunta(rs.getString(2));
                  perg.setAssunto(rs.getString(3));
                  
                  pergunta.add(perg);
                  count++;
               }
               count = 0;
               ret.add( pergunta );
            }
        } catch (Exception e) {
            System.out.println("Erro : " +e.getMessage());
        } finally {
            this.releaseConnection(cn);
        }
        return ret;
    }
    
    public static void main( String[] args )
   {
       System.out.println(Data.getDataAtualSegundos1998());
       System.out.println(Data.getDataAtualSegundos1998());
       System.out.println(Data.getDataAtualSegundos1998());
       

   }
}