function selectCampo()
{
  var check1 = document.getElementById(7);
  check1.checked=true;
}

function marcaLinha(i)
{
  var linha = document.getElementById('linha' + i);
  var check = document.getElementById('check' + i);
  if( check.checked)
  {
    linha.className = 'marcado';
  }
  else
  {
    linha.className = 'acompanhamento' + i%2;
  }
}

function checkCampoText( i, j )
{
  var check1 = document.getElementById(i);
  var check2 = document.getElementById(j);
  if( check1.disabled && check2.disabled )
  {
    check1.value = '';
    check2.value = '';
    check1.disabled = true;
    check2.disabled = true;
    check1.disabled = false;
    check2.disabled = false;
  }
  else
  {
    check1.value = '';
    check2.value = '';
    check1.disabled = true;
    check2.disabled = true;
  }
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

function openpopup()
{
  if (confirm ("TEM CERTEZA QUE DESEJA IMPRIMIR A SIMULA??O DAS ANTECIPA??ES E O CONTRATO?"))
  {
    alert("PARA EFETIVAR ANTECIPA??O, ? NECESS?RIA A APROVA??O DO BANCO. FAVOR IMPRIMIR RELAT?RIO");
    var popurl="imprimir.jsp";
    winpops=window.open(popurl);
    chamaSubmit();
  }
}

function chamaSubmit()
{
  document.forms[0].submit();
}