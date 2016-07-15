package com.br.univali.quiz.utils;

/**
 * @author jean
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class Valida
{

   public boolean validaCpf(String cpf)
   {
      int soma = 0;

      try
      {
         Long.parseLong(cpf);
      }
      catch (Exception e)
      {
         return false;
      }
      if (cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333")
            || cpf.equals("44444444444") || cpf.equals("55555555555") || cpf.equals("66666666666")
            || cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999")
            || cpf.equals("00000000000"))
      {
         return false;
      }
      if (cpf.length() == 11)
      {
         for (int i = 0; i < 9; i++)
            soma += (10 - i) * (cpf.charAt(i) - '0');
         soma = 11 - (soma % 11);
         if (soma > 9)
            soma = 0;
         if (soma == (cpf.charAt(9) - '0'))
         {
            soma = 0;
            for (int i = 0; i < 10; i++)
               soma += (11 - i) * (cpf.charAt(i) - '0');
            soma = 11 - (soma % 11);
            if (soma > 9)
               soma = 0;
            if (soma == (cpf.charAt(10) - '0'))
            {
               return true;
            }
         }
      }
      return false;
   }

   public boolean validaCnpj(String cnpj)
   {
      int soma = 0;

      try
      {
         Long.parseLong(cnpj);
      }
      catch (Exception e)
      {
         return false;
      }
      if (cnpj.length() == 14)
      {
         for (int i = 0, j = 5; i < 12; i++)
         {
            soma += j-- * (cnpj.charAt(i) - '0');
            if (j < 2)
               j = 9;
         }
         soma = 11 - (soma % 11);
         if (soma > 9)
            soma = 0;
         if (soma == (cnpj.charAt(12) - '0'))
         {
            soma = 0;
            for (int i = 0, j = 6; i < 13; i++)
            {
               soma += j-- * (cnpj.charAt(i) - '0');
               if (j < 2)
                  j = 9;
            }
            soma = 11 - (soma % 11);
            if (soma > 9)
               soma = 0;
            if (soma == (cnpj.charAt(13) - '0'))
            {
               return true;
            }
         }
      }
      return false;
   }
   
   public static void main(String[] args)
   {
      Valida v = new Valida();
      
      System.out.println(v.validaCpf("11111111111"));
      System.out.println(v.validaCnpj("91235549000706"));
   }
}