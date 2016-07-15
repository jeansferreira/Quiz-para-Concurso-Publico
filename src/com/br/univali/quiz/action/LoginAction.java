package com.br.univali.quiz.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import com.br.univali.quiz.persistence.PersistenceDados;

public class LoginAction extends Action
{
   
	// --------------------------------------------------------- Methods
	/** 
	 * Method execute
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
      DynaActionForm loginForm = ( DynaActionForm ) form;
		HttpSession session = request.getSession();
		ActionErrors errors = new ActionErrors();
		PersistenceDados persistence = new PersistenceDados();
		
		session.removeAttribute("AdmSessao");
		
		if (loginForm.get( "login" ).equals(""))
		{
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Usuário"));
			saveMessages(request, errors);
		}
		if (loginForm.get( "password" ).equals(""))
		{
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Senha"));
			saveMessages(request, errors);
		}
		
		if (errors.size() != 0) {
			return (new ActionForward(mapping.getInput()));
		}
        
        boolean existUser = persistence.findUser(loginForm.get( "login" ).toString(),loginForm.get( "password" ).toString());
        
        if (existUser == false)
        {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("erro.dados"));
			saveMessages(request, errors);
        }
        else
        {
        	session.setAttribute("AdmSessao","usuarioNaSessao");
        }

        if (errors.size() != 0)
        {
           return (new ActionForward(mapping.getInput()));
        }
        
		loginForm.reset( mapping, request );
        
		return mapping.findForward("sucesso");
	}
}