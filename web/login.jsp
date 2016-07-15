<%@ include file="cabecalho.jsp"%>
<%@ include file="menu.jsp"%>

  <TR>
    <td>
	  <table width="20%" align="center"  class="titulo_area" border="0">
	    <tr>
	      <td align="center"><br></td>
	    </tr>
	  </table>
    </td>
  </TR>
  
  <TR>
    <td>
	  <table width="50%" align="center" class="titulo_area" border="0">
	    <tr>
	      <td align="center"><bean:message key="page.index.topico"/></td>
	    </tr>
	  </table>
	    </td>
	  </TR>
	
	  <TR>
	    <td>
	  <html:form action="/login" focus="login">
	
	  <table align="center" class="tabela_pesquisa" border="0" width="30%">
	    <tr>
	      <td>
	        <table align="center" border="0">
	          <tr>
	            <td height="10">&nbsp;</td>
	            <td height="10">&nbsp;</td>
	          </tr>
	          <tr>
	            <td align="center"><bean:message key="page.index.nome"/></td>
	            <td align="center"><html:text styleClass="input" property="login"/></td>
	          </tr>
	          <tr>
	            <td height="10">&nbsp;</td>
	            <td height="10">&nbsp;</td>
	          </tr>
	          <tr>
	            <td align="center"><bean:message key="page.index.password"/></td>
	            <td align="center"><html:password styleClass="input" property="password"/>
	            </td>
	          </tr>
	        </table>
	      </td>
	    </tr>
	    <tr>
	      <td>
	        <table align="center" border="0">
	          <tr>
	            <td align="center">
	              <html:submit styleClass="botao">Entrar</html:submit></td>
	          </tr>
	        </table>
	      </td>
	    </tr>
	  </table>
	  
	  <table width="50%" align="center">
	    <tr>
	      <td align="center">
	        <logic:messagesPresent message="true">
	 		<table align="center">
	 		<tr>
	 		  <td class="msg_erro">
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
	
	  </html:form>
    </td>
  </TR>
      
<%@ include file="rodape.jsp"%>