<%@ include file="cabecalho.jsp"%>

<!-- Inicio  -->
<logic:present name="AdmSessao" scope="session">
  <%@ include file="menuAdm.jsp"%>
  
  <table width="20%" align="center"  class="titulo_area" border="0">
    <tr>
      <td align="center"><br></td>
    </tr>
  </table>
  
  <table width="50%" align="center" border="0">
    <tr>
      <td align="center">
        <logic:messagesPresent message="true">
 		<table width="100%" align="center">
 		<tr>
 		  <td class="msg_erro" align="center">
 		    <ul>
 		      <html:messages id="errors" message="true">
 		        <li><bean:write name="errors"/></li>
 		      </html:messages>
 		    </ul>
 		  </td>
 		<tr>
 	    </table>
 	    </logic:messagesPresent>
 	  </td>
    </tr>
  </table>

  <table width="50%" align="center" class="titulo_area" border="0">
    <tr>
      <td align="center"><bean:message key="page.insertUser.topico"/></td>
    </tr>
  </table>
  
  <br>
  <br>

  <html:form action="/insertUser" focus="nome">

  <table align="center" class="tabela_pesquisa" border="0" width="50%">
    <tr>
      <td>
        <table align="center" border="0" width="100%">
          <tr>
            <td height="10">&nbsp;</td>
            <td height="10">&nbsp;</td>
          </tr>
          <tr>
            <td align="left">&nbsp;<bean:message key="page.insertUser.nome"/></td>
            <td align="center"><html:text styleClass="input" property="nome"/></td>
          </tr>
          <tr>
            <td height="10">&nbsp;</td>
            <td height="10">&nbsp;</td>
          </tr>
          <tr>
            <td align="left">&nbsp;<bean:message key="page.insertUser.senha"/></td>
            <td align="center"><html:text styleClass="input" property="senha"/></td>
          </tr>
          <tr>
            <td height="10">&nbsp;</td>
            <td height="10">&nbsp;</td>
          </tr>
          <tr>
            <td align="left">&nbsp;<bean:message key="page.insertUser.cfSenha"/></td>
            <td align="center"><html:text styleClass="input" property="cfSenha"/></td>
            
          </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td>
        <table align="center" border="0">
          <tr>
            <td align="center">
              <html:submit styleClass="botao"><bean:message key="page.insertUser.insert"/></html:submit>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  <table align="center">
    <tr>
      <td>&nbsp;</td>
    </tr>
  </table>
  
  </html:form>  
</logic:present>
<!-- Fim -->

<!-- Inicio  -->
<logic:notPresent name="AdmSessao" scope="session">
  <%@ include file="menuPrinc.jsp"%>
  
    <table width="50%" align="center" border="0">
    <tr>
      <td>&nbsp;</td>
    </tr>
  </table>
  
  <table width="40%" align="center" class="tabela_pesquisa" border="0">
    <tr>
      <td width="20%" align="center"><img src="imagens/atencao.gif"></td>
    </tr>
    <tr>
      <td width="20%" align="center">&nbsp;</td>
    </tr>
    <tr>
      <td valign="middle" width="80%" align="center">&nbsp;&nbsp;Tentativa de acesso ao site negado!</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
  </table>
</logic:notPresent>
<!-- Fim  -->

<%@ include file="rodape.jsp"%>