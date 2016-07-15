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
import org.apache.struts.action.DynaActionForm;

import com.br.univali.quiz.dao.EstruturaDAO;
import com.br.univali.quiz.persistence.PersistenceDados;

public class ListaQuizAction extends Action
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

      DynaActionForm listaQuizForm = ( DynaActionForm ) form;
      HttpSession session          = request.getSession();
      ActionErrors errors          = new ActionErrors();
      PersistenceDados persistence = new PersistenceDados();

      ArrayList ar = new ArrayList();
      ar = ( ArrayList ) session.getAttribute( "ListaEstrutura" );

      ArrayList lista = new ArrayList();
      lista = this.findAssunto( ar , listaQuizForm );

      ArrayList listaPergunta = new ArrayList();
      listaPergunta = ( ArrayList ) persistence.findListQuiz( lista );

      if( listaPergunta.size() != 0 )
      {
         session.setAttribute( "ListaPerguntas" , listaPergunta );
      }
      else
      {
         session.removeAttribute( "ListaPerguntas" );
      }

      listaQuizForm.reset( mapping , request );

      return mapping.findForward( "sucesso" );
   }

   /**
    * Metodo que retorna as empresa selecionadas no combo no JSP.
    * 
    * @param ar Contem a lista das empresas que estão na sessão.
    * @return Uma string com todos id das empresas selecionadas.
    */
   private ArrayList findAssunto( ArrayList ar, DynaActionForm listaQuizForm )
   {
       ArrayList array = new ArrayList();

       if ( listaQuizForm.get( "gpEstrutura" ) != null &&
                ((String[])listaQuizForm.get( "gpEstrutura" )).length > 0 )
       {
           int j = 0;
           int i = ((String[])listaQuizForm.get( "gpEstrutura" )).length;
           String[] str = ((String[])listaQuizForm.get( "gpEstrutura" ));
           
           if (i > 0)
           {
               array.add(str[j]);
               j++;

               while (j < i)
               {
                   array.add(str[j]);
                   j++;
               }
           }
       } else
       {
           for (int i = 0; i < ar.size(); i++)
           {
              EstruturaDAO est = new EstruturaDAO();
              est = ( EstruturaDAO ) ar.get( i );

              array.add( est.getId() );
           }
       }
       return array;
   }
}