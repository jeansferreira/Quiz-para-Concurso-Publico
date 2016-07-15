package com.br.univali.quiz.utils;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.TimeZone;

public class Data
{

  public Data()
  {
  }
  /**
   * Pega a data atual no formato especificado no parâmetro.
   * Paremetros tratado no formato:
   * %D = dia do mes
   * %M = mes do ano
   * %y = ano com 2 digitos
   * %Y = ano com 4 digitos
   * %H = hora em 24 horas
   * %h = hora em 12 horas
   * %m = minutos
   * %s = segundos
   * %n = milissegundos
   * %ampm = retorna AP ou PM dependendo da hora
   * %wy = semana do ano
   * %wm = semana do mes
   * %dy = dia do ano
   * %dw = dia da semana
   */
  public static String getDataAtualMaisDias(String formato, int dias)
  {
    Calendar calend = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
    calend.add(Calendar.DATE, dias);
    return formataData(formato, calend);
  }

  public static String getDataAtual(String formato)
  {
    Calendar calend = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
    return formataData(formato, calend);
  }


  public static String formataData(String formato, Calendar calend)
  {
    NumberFormat nf = NumberFormat.getInstance();

    String ret = formato;
    nf.setMaximumIntegerDigits(2);
    nf.setMinimumIntegerDigits(2);
    ret = ret.replaceAll("%D", nf.format(calend.get(Calendar.DAY_OF_MONTH)));
    ret = ret.replaceAll("%M", nf.format(calend.get(Calendar.MONTH ) + 1));
    ret = ret.replaceAll("%y", nf.format(calend.get(Calendar.YEAR)));
    ret = ret.replaceAll("%H", nf.format(calend.get(Calendar.HOUR_OF_DAY)));
    ret = ret.replaceAll("%h", nf.format(calend.get(Calendar.HOUR)));
    ret = ret.replaceAll("%m", nf.format(calend.get(Calendar.MINUTE)));
    ret = ret.replaceAll("%s", nf.format(calend.get(Calendar.SECOND)));
    if (calend.get(Calendar.AM_PM) == Calendar.AM)
      ret = ret.replaceAll("%ampm", "AM");
    else
      ret = ret.replaceAll("%ampm", "PM");
    ret = ret.replaceAll("%wy", nf.format(calend.get(Calendar.WEEK_OF_YEAR)));
    ret = ret.replaceAll("%wm", nf.format(calend.get(Calendar.WEEK_OF_MONTH)));
    ret = ret.replaceAll("%dw", getDiaSemana(calend.get(Calendar.DAY_OF_WEEK)));
    nf.setMaximumIntegerDigits(4);
    ret = ret.replaceAll("%Y", nf.format(calend.get(Calendar.YEAR)).replaceAll(",", "")); //q chuncho horroroso!!!
    nf.setMinimumIntegerDigits(3);
    ret = ret.replaceAll("%n", nf.format(calend.get(Calendar.MILLISECOND)));
    ret = ret.replaceAll("%dy", nf.format(calend.get(Calendar.DAY_OF_YEAR)));

    return ret;
  }

  /**
   * Retorna uma string com o dia da semana
   */
  public static String getDiaSemana(int dia)
  {
    String ret = new String();
    switch (dia)
    {
      case Calendar.SUNDAY:
        ret = "Domingo";
      break;
      case Calendar.MONDAY:
        ret = "Segunda-feira";
      break;
      case Calendar.TUESDAY:
        ret = "Terça-feira";
      break;
      case Calendar.WEDNESDAY:
        ret = "Quarta-feira";
      break;
      case Calendar.THURSDAY:
        ret = "Quinta-feira";
      break;
      case Calendar.FRIDAY:
        ret = "Sexta-feira";
      break;
      case Calendar.SATURDAY:
        ret = "Sábado";
      break;
      default:
        ret = null;
      break;
    }
    return ret;
  }

	/**
	 * Função para validar se uma string esta no formato dd/mm/aaaa.
	 * @param String data
	 * @return 0 para data valida.
	 *				 1 para data inválida.
	 *				 2 para ano inválido.
	 *				 3 para mês inválido.
	 *				 4 para dia inválido.
	 */
	public static int validaData(String data)
	{
		try
		{
			StringTokenizer token = new StringTokenizer(data, "/");
			if (token.countTokens() == 3)
			{
				int dia = Integer.parseInt(token.nextToken());
				int mes = Integer.parseInt(token.nextToken());
				int ano = Integer.parseInt(token.nextToken());

				if ((ano >= 1970) && (ano <= 2050))
				{
					switch (mes)
					{
						case 1: case 3: case 5: case 7: case 8: case 10: case 12:
						  if ((dia <= 0) || (dia > 31))
							return 4;
						break;
						case 4: case 6: case 9: case 11:
							if ((dia <= 0) || (dia > 30))
							  return 4;
						break;
						case 2:
							if ((dia <= 0) || (dia > 29))
								return 4;
						break;
						default:
						  return 3;
					}
				}
				else
				{
					return 2;
				}
			}
			else
			{
				return 1;
			}
		}
		catch(Exception e)
		{
			return 1;
		}
		return 0;
	}

  public static boolean isValidDate(String dateValue)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setLenient(false);
    try
    {
      sdf.parse(dateValue);
      return true;
    }
    catch (ParseException ex)
    {
      return false;
    }
  }

  /**
   * Retorna a data autal em segundos desde 01/01/1998 0:00:00.
   */
  public static long getDataAtualSegundos1998()
  {
    /*Timestamp da data atual*/
    Calendar calend = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
    long dataAtual = calend.getTimeInMillis();

    /*Timestamp de 01/01/1998 0:00:00*/
    calend.set(1998, 1, 1, 0, 0, 0);
    long dataBase = calend.getTimeInMillis();

    return (dataAtual - dataBase) / 1000;
  }

  public static void main (String args[])
  {
    System.out.println(Data.getDiaSemana(1));
  }


}
