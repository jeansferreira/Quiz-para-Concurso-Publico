package com.br.univali.quiz.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.DynaActionForm;

import com.br.univali.quiz.dao.RespostaDAO;
import com.br.univali.quiz.persistence.PersistenceDados;

public class InsertQuizAction extends Action
{

   // --------------------------------------------------------- Methods
   /**
    * Method execute
    * 
    * @param ActionMapping mapping
    * @param ActionForm form
    * @param HttpServletRequest request
    * @param HttpServletResponse response
    * @return ActionForward
    * @throws Exception
    */
   public ActionForward execute( ActionMapping mapping , ActionForm form ,
            HttpServletRequest request , HttpServletResponse response ) throws Exception
   {
      DynaActionForm insertQuizForm = ( DynaActionForm ) form;
      ActionErrors errors = new ActionErrors();
      HttpSession session = request.getSession();
      PersistenceDados persistence = new PersistenceDados();
      String redirect = "";

      ArrayList listaResposta = new ArrayList();
      listaResposta = ( ArrayList ) session.getAttribute( "ListaResposta" );
      
      String submit = request.getParameter("submit");
      System.out.println("Submit : "+submit);
      
      if (submit.equals("Adicionar Assunto"))
      {
         if( insertQuizForm.get( "assunto" ).equals( "" ) )
         {
            errors.add( ActionErrors.GLOBAL_MESSAGE , new ActionMessage( "errors.required" ,
                     "Novo Assunto" ) );
            saveMessages( request , errors );
         }
         else
         {
            boolean insert = persistence.insertNovoAssunto( insertQuizForm.get( "assunto" ).toString() );

            if( insert )
            {
               session.removeAttribute( "ListaEstrutura" );
               ArrayList lista = new ArrayList();
               lista = persistence.findAssunto();
               session.setAttribute( "ListaEstrutura" , lista );
            }
            else
            {
               errors.add( ActionErrors.GLOBAL_MESSAGE , new ActionMessage( "insertAssunto.dados" ) );
               saveMessages( request , errors );
            }

            session.removeAttribute( "ListaEstrutura" );
            ArrayList lista = new ArrayList();
            lista = persistence.findAssunto();
            session.setAttribute( "ListaEstrutura" , lista );
         }
         
         insertQuizForm.getMap().clear();
      }
      
      if (submit.equals("Adicionar Resposta"))
      {
         ArrayList lista = new ArrayList();

         if( session.getAttribute( "ListaResposta" ) != null )
         {
            lista = ( ArrayList ) session.getAttribute( "ListaResposta" );
         }
         if( insertQuizForm.get( "resposta" ).equals( "" ) )
         {
            errors.add( ActionErrors.GLOBAL_MESSAGE , new ActionMessage( "errors.required" ,"Nova resposta" ) );
            saveMessages( request , errors );
         }
         else
         {
            RespostaDAO res = new RespostaDAO();
            res.setResposta( insertQuizForm.get( "resposta" ).toString() );

            if( session.getAttribute( "ListaResposta" ) == null )
            {
               res.setId( String.valueOf( "0" ) );
            }
            else
            {
               res.setId( String.valueOf( lista.size() ) );
            }

            lista.add( res );
            session.setAttribute( "ListaResposta" , lista );
         }
      }

      if (submit.equals("Cadastrar"))
      {
         if( insertQuizForm.get( "listaAssunto" ).toString().equals( "" ) )
         {
            errors.add( ActionErrors.GLOBAL_MESSAGE , new ActionMessage( "errors.required" , " Selecionar um Assunto " ) );
            saveMessages( request , errors );
         }
         if( listaResposta == null )
         {
            errors.add( ActionErrors.GLOBAL_MESSAGE , new ActionMessage( "errors.required" ,
                     " Resposta " ) );
            saveMessages( request , errors );
         }
         if( insertQuizForm.get( "pergunta" ) == null
                  || insertQuizForm.get( "pergunta" ).toString().equals( "" ) )
         {
            errors.add( ActionErrors.GLOBAL_MESSAGE , new ActionMessage( "errors.required" ,
                     " Pergunta " ) );
            saveMessages( request , errors );
         }
         if( insertQuizForm.get( "nivel" ) == null || insertQuizForm.get( "nivel" ).toString().equals( "" ) )
         {
            errors.add( ActionErrors.GLOBAL_MESSAGE ,
                     new ActionMessage( "errors.required" , " Nível " ) );
            saveMessages( request , errors );
         }

         if( errors.size() != 0 )
         {
            return ( new ActionForward( mapping.getInput() ) );
         }

         boolean insert = persistence.insertQuiz( insertQuizForm.get( "listaAssunto" ).toString() ,
                  insertQuizForm.get( "pergunta" ).toString() , listaResposta , insertQuizForm.get( "nivel" ).toString() );

         if( !insert )
         {
            errors.add( ActionErrors.GLOBAL_MESSAGE , new ActionMessage( "problema.sistema" ) );
            saveMessages( request , errors );
         }
         
         session.removeAttribute( "ListaResposta" );
         session.removeAttribute( "ListaPerguntas" );
         insertQuizForm.getMap().clear();
      }
      
      return mapping.findForward( "sucesso" );
   }

}
