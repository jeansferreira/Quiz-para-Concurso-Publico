<%@ include file="cabecalho.jsp"%>
<%@ include file="menu.jsp"%>

<script>
  function marcaLinha(i)
    {
      var linha = document.getElementById('linha' + i);
      var check = document.getElementById('check' + i);
      if( check.checked)
      {
        linha.className = 'tr_marcaLinha';
      }
      else
      {
        linha.className = 'tr_acompanhamento' + i%2;
      }
    }
</script>

  <logic:present name="ListaEstrutura" scope="session">

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
	  <table width="50%" align="center"  class="titulo_area" border="0">
	    <tr>
	      <td align="center"><bean:message key="page.pesquisa.topico"/></td>
	    </tr>
	  </table>
    </td>
  </TR>

  <TR>
    <td>
	  <html:form action="/pesquisa">
	
	  <table width="70%" align="center" class="tabela_pesquisa" border="0">
	    <tr>
	      <td align="center" class="select">Selecione o(s) <i>Assunto(s)</i> para gerar as perguntas</td>
	    </tr>
	    
	    <tr>
	      <td height="10">&nbsp;</td>
	    </tr>
	    
	    <logic:iterate id="Estrutura" indexId="i" name="ListaEstrutura" scope="session">
	    <tr height="10" id="linha<%=i%>" class="tr_acompanhamento<%=i.intValue()%2%>" >
	      <td width=20%" align='left'>
	        <html:multibox property="lista" onclick='<%="marcaLinha("+i+")"%>' styleId='<%="check"+i%>'>
	          <bean:write name="Estrutura" property="id" filter="true" />
	        </html:multibox>
	          <bean:write name="Estrutura" property="nome" filter="true" />
	      </td>
	    </tr>
	    </logic:iterate>
	    
	    <tr>
	      <td height="10">&nbsp;</td>
	    </tr>
	    
	    <tr>
	      <td align="center" class="select">Selecione o <i>NÍVEL</i> das perguntas que deseja gerar</td>
	    </tr>
	    
	    <tr>
	      <td height="10">&nbsp;</td>
	    </tr>
	    
	    <tr>
	      <td align="center">
	        <html:radio styleId="7" property="nivel" value="0">N&iacute;vel Iniciante</html:radio>
	        &nbsp;&nbsp;&nbsp;&nbsp;
	        <html:radio styleId="8" property="nivel" value="1">N&iacute;vel Intermedi&aacute;rio</html:radio>
	        &nbsp;&nbsp;&nbsp;&nbsp;        
	        <html:radio styleId="9" property="nivel" value="2">N&iacute;vel Avançado</html:radio>                
	      </td>
	    </tr>
	    
	    <tr>
	      <td height="10">&nbsp;</td>
	    </tr>
	    
	    <tr>
	      <td align="center"><html:submit styleClass="botao">Gerar Perguntas</html:submit></td>
	    </tr>
	  </table>
    </td>
  </TR>

  <TR>
    <td>
	  <table width="50%" align="center">
	    <tr>
	      <td align="center" class="msg_erro"><html:errors/></td>
	    </tr>
	  </table>
    </td>
  </TR>

  <logic:notPresent name="checked">
    <script language="javaScript">
      selectCampo();
    </script>
  </logic:notPresent>
    
  </html:form>

</logic:present>

  
<!-- Inicio  -->
<logic:notPresent name="ListaEstrutura" scope="session">

  <TR>
    <TD height="25">
	  <table align="center">
	    <tr>
	      <td>&nbsp;</td>
	    </tr>
	  </table>
    </TD>
  </TR>

  <TR>
    <TD height="25">
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
    </TD>
  </TR>
</logic:notPresent>
<!-- Fim  -->
      
<%@ include file="rodape.jsp"%>