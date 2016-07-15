package com.br.univali.quiz.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.br.univali.quiz.dao.RespostaDAO;

public class RemoverRespostaListaAction extends Action
{

   // --------------------------------------------------------- Methods
   /**
    * Method executa ação que remove da lista de respostas um item da lista.
    * 
    * 
    * @param ActionMapping mapping
    * @param ActionForm form
    * @param HttpServletRequest request
    * @param HttpServletResponse response
    * @return ActionForward
    * @throws Exception
    */
   public ActionForward execute( ActionMapping mapping , ActionForm form ,HttpServletRequest request , HttpServletResponse response ) throws Exception
   {
      HttpSession session = request.getSession();

      ArrayList listaResposta = new ArrayList();
      ArrayList listaRespostaSaida = new ArrayList();
      listaResposta = ( ArrayList ) session.getAttribute( "ListaResposta" );
      
      String id = (String) request.getParameter("id");
      
      listaResposta.remove( Integer.parseInt( id ) );
      
      if ( listaResposta.size() == 0 )
      {
         session.removeAttribute( "ListaResposta" );
      }
      else
      {
         for( int i = 0 ; i < listaResposta.size() ; i++ )
         {
            RespostaDAO res = new RespostaDAO();
            res = (RespostaDAO)listaResposta.get(i);
            res.setId( String.valueOf(i) );
            listaRespostaSaida.add(res);            
         }
         session.setAttribute( "ListaResposta", listaRespostaSaida );
      }

      return mapping.findForward( "sucesso" );
   }
}