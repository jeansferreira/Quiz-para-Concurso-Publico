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
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import com.br.univali.quiz.persistence.PersistenceDados;

public class ListaAssuntoAction extends Action
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
      DynaActionForm listaAssuntoForm = ( DynaActionForm ) form;
		HttpSession session = request.getSession();
		ActionErrors errors = new ActionErrors();
		PersistenceDados persistence = new PersistenceDados();
		
		session.removeAttribute("ListaEstrutura");
		
		ArrayList lista = new ArrayList();
		lista = persistence.findAssunto();
		
		if (lista.size() > 0)
		{
			session.setAttribute("ListaEstrutura", lista);
		}
		else
		{
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("find.dados"));
			saveMessages(request, errors);
		}
		
        if (errors.size() != 0)
        {
           return (new ActionForward(mapping.getInput()));
        }
        
        listaAssuntoForm.reset(mapping, request);
        
        session.removeAttribute("ListaResposta");
        session.removeAttribute("ListaPerguntas");

		return mapping.findForward("sucesso");
	}

}
