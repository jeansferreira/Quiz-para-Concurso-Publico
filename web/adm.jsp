<%@ include file="cabecalho.jsp"%>

<logic:present name="AdmSessao" scope="session">
  <%@ include file="menuAdm.jsp"%>

</logic:present>


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

<%@ include file="rodape.jsp"%>