package com.br.univali.quiz.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import com.br.univali.quiz.persistence.PersistenceDados;

public class InsertUserAction extends Action
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

      DynaActionForm insertUserForm = ( DynaActionForm ) form;
      ActionErrors errors = new ActionErrors();
      PersistenceDados persistence = new PersistenceDados();

      if( insertUserForm.get( "nome" ).equals( "" ) )
      {
         errors
                  .add( ActionMessages.GLOBAL_MESSAGE , new ActionMessage( "errors.required" ,
                           "Nome" ) );
         saveMessages( request , errors );
      }
      if( insertUserForm.get( "senha" ).equals( "" ) )
      {
         errors
                  .add( ActionErrors.GLOBAL_MESSAGE , new ActionMessage( "errors.required" ,
                           "Senha" ) );
         saveMessages( request , errors );
      }
      if( insertUserForm.get( "cfSenha" ).equals( "" ) )
      {
         errors.add( ActionErrors.GLOBAL_MESSAGE , new ActionMessage( "errors.required" ,
                  "Confirmar Senha" ) );
         saveMessages( request , errors );
      }

      if( errors.size() != 0 )
      {
         return ( new ActionForward( mapping.getInput() ) );
      }

      if( !insertUserForm.get( "senha" ).equals( insertUserForm.get( "cfSenha" ).toString() ) )
      {
         errors.add( ActionErrors.GLOBAL_MESSAGE , new ActionMessage( "errors.invalid" ,
                  "Confirmar Senha" , insertUserForm.get( "senha" ).toString() , insertUserForm.get( "cfSenha" ).toString() ) );
         saveMessages( request , errors );
      }

      if( errors.size() != 0 )
      {
         return ( new ActionForward( mapping.getInput() ) );
      }

      boolean inserir = persistence.insertUser( insertUserForm.get( "nome" ).toString() ,insertUserForm.get( "senha" ).toString() );

      if( !inserir )
      {
         errors.add( ActionErrors.GLOBAL_MESSAGE , new ActionMessage( "insertUser.dados" ) );
         saveMessages( request , errors );
      }

      if( errors.size() != 0 )
      {
         return ( new ActionForward( mapping.getInput() ) );
      }

      insertUserForm.getMap().clear();

      return mapping.findForward( "sucesso" );
   }

}
