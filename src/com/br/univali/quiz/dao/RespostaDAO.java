package com.br.univali.quiz.dao;

import java.io.Serializable;


public class RespostaDAO implements Serializable
{
   private String resposta;
   
   private String id;
   
   public String getResposta()
   {
   
      return resposta;
   }

   
   public void setResposta( String resposta )
   {
   
      this.resposta = resposta;
   }


   
   public String getId()
   {
   
      return id;
   }


   
   public void setId( String id )
   {
   
      this.id = id;
   }

}
