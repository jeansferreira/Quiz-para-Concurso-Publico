<%@ include file="cabecalho.jsp"%>

<script language="JavaScript">
function chamaSubmit0()
{
   document.forms[0].submit();
}
function chamaSubmit1()
{
   document.forms[1].submit();
}
function chamaSubmit2()
{
   document.forms[2].submit();
}
function ChequearTodos(chkbox)
{
  for (var i=0;i < document.forms[0].elements.length;i++)
  {
    var elemento = document.forms[0].elements[i];
    if (elemento.type == "checkbox")
    {
      elemento.checked = chkbox.checked
    }
  }
}

</SCRIPT>

<!-- Inicio  -->
<logic:present name="AdmSessao" scope="session">
  <%@ include file="menuAdm.jsp"%>
  
  <TR>
    <TD height="25">
	  <table width="20%" align="center" class="titulo_area" border="0">
	    <tr>
	      <td align="center"><br></td>
	    </tr>
	  </table>
    </TD>
  </TR>

  <TR>
    <TD height="25">
	  <table width="50%" align="center" border="0">
	    <tr>
	      <td align="center">
	        <logic:messagesPresent message="true">
	 		<table width="50%" align="center" class="sec_error">
	 		<tr>
	 		  <td align="center">
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
    </TD>
  </TR>
  
  <TR>
    <TD height="25">
	  <table width="50%" align="center" class="titulo_area" border="0">
	    <tr>
	      <td align="center">Perguntas e Resposta</td>
	    </tr>
	  </table>
    </TD>
  </TR>

  <TR>
    <TD height="25">
	  <table width="50%" align="center" border="0">
	    <tr>
	      <td align="center">&nbsp;</td>
	    </tr>
	  </table>
    </TD>
  </TR>

  <html:form action="/listaQuiz">
  
  <TR>
    <TD width="20%">
      <table align="center" border="0" width="90%" class="tabela_pesquisa">
        <tr>
          <td width="30%" valign="top">
            <table border="0" class="tabela_pesquisa">
            
            <logic:present name="ListaEstrutura" scope="session">
              <tr>
                <td height="10" align="center"><li>Escolha o(s) assunto(s) desejado(s)</li></td>
              </tr>
              <tr>
                <td height="10" align="left">
                  <input type="checkbox" name="checkbox11" value="checkbox" onClick="ChequearTodos(this);">
                  Marcar Todos
                </td>
              </tr>
              <logic:iterate id="Estrutura" indexId="i" name="ListaEstrutura" scope="session">
              <tr height="10" id="linha<%=i%>" class="tr_acompanhamento<%=i.intValue()%2%>" >
                  <td width=20%" align='left'>
                    <html:multibox property="gpEstrutura" onclick='<%="marcaLinha("+i+")"%>' styleId='<%="check"+i%>'>
                      <bean:write name="Estrutura" property="id" filter="true" />
                    </html:multibox>
                      <bean:write name="Estrutura" property="nome" filter="true" />
                  </td>
              </tr>
              </logic:iterate>
              
            </logic:present>
                
            <logic:notPresent name="ListaEstrutura" scope="session">
              <tr>
                <td height="10" class="sem_dados_sessao">Nenhum Assunto cadastrado!</td>
              </tr>
            </logic:notPresent>
            
            </table>
          </td>
          
          <td width="70%" valign="top" align="center">
            <table class="tabela_pesquisa" width="100%">

            <tr>
              <td height="10"><li>Lista da(s) Pergunta(s)</li></td>
            </tr>
              
            <logic:present name="ListaPerguntas" scope="session">
              <tr>
                <td height="10">&nbsp;</td>
              </tr>
              <logic:iterate id="Lista" name="ListaPerguntas" scope="session">
                <logic:iterate indexId="i" name="Lista" id="Pergunta">
                <logic:equal name="Pergunta" property="count" value="0">
                  <tr>
                    <td width=20%" align='left'>
                      <li><bean:write name="Pergunta" property="assunto" filter="true" /></li>
                    </td>
	              </tr>
                </logic:equal>
                  <tr height="10" id="linha<%=i%>" class="tr_acompanhamento<%=i.intValue()%2%>" >
                    <td width=20%" align='left'>
                      <bean:write name="Pergunta" property="pergunta" filter="true" />
                    </td>
	              </tr>
	            </logic:iterate>
              </logic:iterate>
              
            </logic:present>
                
            </table>
          </td>
	    </tr>
	  </table>
    </TD>
  </TR>

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
	  <table align="center">
	    <tr>
	      <td>
	        <html:submit styleClass="botao">Pesquisar</html:submit>
	      </td>  
	    </tr>
	  </table>
    </TD>
  </TR>
  
  </html:form>

  <TR>
    <TD height="25">
	  <table align="center">
	    <tr>
	      <td>&nbsp;</td>
	    </tr>
	  </table>
    </TD>
  </TR>

</logic:present>
<!-- Fim -->

<!-- Inicio  -->
<logic:notPresent name="AdmSessao" scope="session">
  <%@ include file="menuPrinc.jsp"%>

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