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

/**
 * Classe que faz relação da aplicação com a base de dados, busca os dados da
 * base para enviar para WEB.
 * 
 * @author Jean Carlos da Silva Ferreira (jeansferreira@gmail.com)
 * @version 1.0
 * @exception Caso não haja dados na base gera um erro na tela (WEB).
 */
public class BuscaListaAction extends Action
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
      DynaActionForm pesquisaForm = ( DynaActionForm ) form;
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

		pesquisaForm.reset(mapping, request);
		return mapping.findForward("sucesso");
	}
}
