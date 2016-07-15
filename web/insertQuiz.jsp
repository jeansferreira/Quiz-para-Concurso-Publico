<%@ include file="cabecalho.jsp"%>

<!-- Inicio  -->
<logic:present name="AdmSessao" scope="session">
<html:form action="/insertQuiz">
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
	      <td align="center"><bean:message key="page.insertQuiz.topico"/></td>
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
  
  <TR>
    <TD height="25">
	  <table align="center" class="tabela_pesquisa" border="0" width="80%">
	    <tr>
	      <td>
	        <table align="center" border="0" width="100%">
	          <tr>
	            <td height="10"><li>Adicione um novo assunto</li></td>
	            <td height="10">&nbsp;</td>
	            <td height="10"><html:text styleClass="input" property="assunto" maxlength="50" size="15"/></td>
	            <td height="10"><html:submit property="submit" styleClass="botao" value="Adicionar Assunto"/></td>
	            <td height="10">&nbsp;</td>
	            <td height="10"><li>Selecione o assunto desejado</li></td>
	            <td height="10">&nbsp;</td>
	              <logic:present name="ListaEstrutura" scope="session">
	                <td height="10">
	                  <!--Inicio da primeiro select-->
	                    <html:select property="listaAssunto" styleClass="select" size="1">
	                      <html:options collection="ListaEstrutura" property="id" labelProperty="nome"/>
	                    </html:select>
	                  <!--Fim do primeiro select-->
	                </td>
	              </logic:present>
	              
	              <logic:notPresent name="ListaEstrutura" scope="session">
	                <td height="10" class="sem_dados_sessao">Nenhum Assunto cadastrado!</td>
	              </logic:notPresent>
	          </tr>
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
	  <table align="center" class="tabela_pesquisa" border="0" width="80%">
	    <tr>
	      <td>
	        <table align="center" border="0" width="100%">
	          <tr>
	            <td height="10" width="60%"><li>Adicione um nova pergunta</li></td>
	            <td height="10" width="40%"><li>Selecione o nível da pergunta</li></td>	            
	          </tr>
	          <tr>
	            <td height="10" width="60%"><html:textarea styleClass="inputArea" property="pergunta" cols="50"/></td>
	            <td height="10" width="40%">
	              Iniciante&nbsp;<html:radio styleId="7" property="nivel" value="0"/>&nbsp;&nbsp;
	              Intermedi&aacute;rio&nbsp;<html:radio styleId="8" property="nivel" value="1"/>&nbsp;&nbsp;
	              Avançado&nbsp;<html:radio styleId="9" property="nivel" value="2"/>
	            </td>
	          </tr>
	        </table>
	        <table align="center" border="0" width="100%">
	          <tr>
	            <td height="10">&nbsp;</td>
	          </tr>
	        </table>
	        <table align="center" border="0" width="100%">
	          <tr>
	            <td width="100%" height="10" colspan="2"><li>Adicione um resposta</li></td>
	          </tr>
	          <logic:notPresent name="ListaResposta" scope="session">
	            <tr>
	              <td width="100%" height="10" class="titulo_tabela" align="center" colspan="2">======== Nenhuma resposta cadastrada! ========</td>
	            </tr>
	          </logic:notPresent>
	          
	          <logic:present name="ListaResposta" scope="session">
	            <tr>
	              <td height="10" align="center" colspan="2" class="titulo_tabela">======== Lista de respostas ========</td>
	            </tr>

	            <tr>
	              <td height="10" align="center" colspan="2">
	                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabela_pesquisa">
	                  <logic:iterate name="ListaResposta" indexId="i" id="Lista">
	                    <tr height="22" id="linha<%=i%>" class="tr_acompanhamento<%=i.intValue()%2%>">
	                      <td width="05%" align="center">&nbsp;<%=i%>&nbsp;</TD>
	                      <td width="85%" align="left"><bean:write name="Lista" property="resposta" filter="true" /></TD>
	                      <td width="10%" align="center">
	                        <html:link action="/removerRespostaLista.do" paramId="id" paramName="Lista" paramProperty="id">
	                          <img src="imagens/error_pq.gif" border="0" title="Remover esta resposta."/>
	                        </html:link>
	                      </TD>
	                    </tr>
	                  </logic:iterate>
	                </table>
	              </td>
	            </tr>
	          </logic:present>
	  
	          <tr>
	            <td height="10">
	              <table widht="100%">
	                <tr>
	                  <td height="10">Digite a Resposta</td>
	                  <td height="10"></td>
	                </tr>
	                <tr>
	                  <td height="10">
	                    <html:text styleClass="input" property="resposta" maxlength="255" size="60"/>
	                  </td>
	                  <td height="10">
	                    <html:submit property="submit" styleClass="botao" value="Adicionar Resposta"/>
	                  </td>
	                </tr>
	              </table>
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
	              <html:submit property="submit" styleClass="botao" value="Cadastrar"/>
	            </td>
	          </tr>
	        </table>
	      </td>
	    </tr>
	  </table>
    </TD>
  </TR>
</html:form>
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

  <logic:notPresent name="checked">
    <script language="javaScript">
      selectCampo();
    </script>
  </logic:notPresent>



<%@ include file="rodape.jsp"%>