package com.br.univali.quiz.dao;

import java.io.Serializable;


public class PerguntaDAO implements Serializable
{
   private String count;
   private String id;
   private String pergunta;
   private String assunto;
   
   public String getAssunto()
   {
   
      return assunto;
   }
   
   public void setAssunto( String assunto )
   {
   
      this.assunto = assunto;
   }
   
   public String getId()
   {
   
      return id;
   }
   
   public void setId( String id )
   {
   
      this.id = id;
   }
   
   public String getPergunta()
   {
   
      return pergunta;
   }
   
   public void setPergunta( String pergunta )
   {
   
      this.pergunta = pergunta;
   }

   
   public String getCount()
   {
   
      return count;
   }

   
   public void setCount( String count )
   {
   
      this.count = count;
   }

}
