/*
 * Created on 08/04/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.br.univali.quiz.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author jean
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FileIO extends File
{

	/**
	 * Criar um objeto da class FileIO.
	 * @param pathname Nome do arquivo mais caminho.
	 */
	public FileIO(String pathname)
	{
		super(pathname);
	}

	/**
	 * Verifica se o arquivo existe e se ele é realmente um
	 * arquivo.
	 * @return "true" se o arquivo existe e "false"
	 * se ele não existe
	 */
	public boolean existFile()
	{
		boolean retorno = false;
		if ((this.exists()) && (this.isFile()))
		{                                                        // se arquivo existe
			retorno = true;
		} else                                                   // Se o arquivo atual não existe
			{
			retorno = false;
		}
		return retorno;
	} 

	/**
	 * Metodo utilizado pelo Metodo copyTo. Copia os bytes de um
	 * arquivo para o outro.
	 * @param from Arquivo atual.
	 * @param to Novo arquivo.
	 * @throws IOException Exceção de entrada e saída.
	 */
	private void copyBytes(File from, File to) throws IOException
	{
		FileInputStream in = new FileInputStream(from);
		FileOutputStream out = new FileOutputStream(to);

		FileChannel cin = in.getChannel();
		cin.transferTo(0, cin.size(), out.getChannel());

		in.close();
		out.close();
	}

	/**
	* Cria uma cópia do arquivo para no path especificado.
	* Caso o arquivo destino já exista lugar pode ser sobrescrito ou não,
	* dependendo do parametro override.
	* @param to Nome do arquivo com o diretorio para onde vai
	* ser copiar o arquivo.
	* @param override Se "true" sobrescreve o arquivo já
	* existente.
	* @throws IOException Exceção de entrada e saida.
	*/
	public void copyTo(String to, boolean override) throws IOException
	{
		File arq_dest = new File(to);

		if (this.existFile())
		{
			if (override)                                         // Se deseja sobrescrever
			{
				if (arq_dest.exists())                             // Se o arquivo de destino existe, sobrescreve ele.
				{
					arq_dest.delete();
				}
				arq_dest.createNewFile();
				this.copyBytes(this, arq_dest);                    //chama função copyBytes
			} else                                                // Senão deseja sobrescrever
				{
				if (!arq_dest.exists())                            // se o arquivo de destino não existe, cria ele.
				{
					arq_dest.createNewFile();
					this.copyBytes(this, arq_dest);

				} else                                             // Se arquivo existe, gere um erro.
					{
					throw (new IOException("Nao foi possivel copiar: O arquivo de destino \"" + to + "\" já existe."));
				}
			}
		} else                                                   // se o arquivo de origem não existe, gere um erro
			{
			throw (new IOException("Não é possível copiar o arquivo \"" + this.getPath() + "\", pois ele não existe."));
		}
	}

	/**
	 * Metodo que conta quantas linhas tem em um arquivo
	 * @param buf Arquivo que será contado
	 * @return A quantidade de linhas dentro do arquivo
	 * @throws IOException Exceções de entrada e saída
	 * @see #getNumberOfLines
	 */
	private int contaNumberOfLines(BufferedReader buf) throws IOException
	{
		//VARIAVEIS
		String line;
		int contador =0;
		
		//CODIGO

		line = buf.readLine();
        		
		while (line != null)
		{
			contador++;
			line = buf.readLine();
		}
		buf.close();
	  return contador; 
	}


	/**
	 * Metodo que passa uma arquivo para que conte a quantidade de linhas contidas
	 * @return Quantidade de linhas dentro do arquivo
	 */
	public int getNumberOfLines() throws IOException
	{
		  //VARIAVEIS
		int contador = 0;
      
		//CODIGO
		if (this.existFile())                                    // se arquivo existe
		{
			FileReader input = new FileReader(this);
			BufferedReader buf = new BufferedReader(input);

			contador = this.contaNumberOfLines(buf);
		} else
		{
			throw (new IOException("Não é possível ler o número de linhas do arquivo \"" + this.getPath() + "\", pois ele não existe."));
		}
		return contador;
	}  

	/**
	 * Metodo para retornar todas as linhas do arquivo
	 * @return Um array de string contendo cada linha do arquivo
	 * @see #read
	 * @see #montaLinha
	*/
	public String[] readLines() throws IOException
	{
		//VARIAVEIS
		String[] retorno = {};

		//CODIGO
		String lines = this.read();
		retorno = lines.split("\\n");
		return retorno;
	}
         
	/**
	 * Metodo que transforma os dados de um arquivo em uma String
	 * @param buf Arquivo recebido
	 * @return Uma String
	 * @throws IOException Exceções de entrada e saída
	 * @see #read
	 */
	private String montaLinha(BufferedReader buf) throws IOException
	{
		//VARIAVEIS
		  String line = "";
		  String retorno = "";
   	  
		  //CODIGO
     
		line = buf.readLine();
		retorno = line;
		line = buf.readLine();    
          
		while (line != null)
		{
			retorno = retorno + "\n" + line;
			line = buf.readLine();
		}
		buf.close();
		return retorno; 	
	}
    
	/**
	* Metodo que passa um arquivo para que seje transformando seus dados em uma string.
	* @return Retorna uma string.
	* @throws IOException Exceção de entrada e saida.
	* @see #retornaReadLine
	*/
	public String read() throws IOException
	{
		  //VARIAVEIS 
		String retorno = "";

		//CODIGO
		if (this.existFile())                                    // se arquivo existe
		{
			FileReader input = new FileReader(this);
			BufferedReader buf = new BufferedReader(input);
			
			retorno = this.montaLinha(buf);
		} else
		{
			throw (new IOException("Não é possível ler a linha \"" + this.getPath() + "\", pois ele não existe."));
		}
		return retorno;
	}
   
	/**
	 * Metodo que recebe uma linha e o numero da linha que será de retorno
	 * @param buf Arquivo com as linhas
	 * @param index Numero da linha que será de retorno
	 * @return Linha que será de retorno
	 * @throws IOException Exceções de entrada e saída 
	 */
	private String retornaReadLine(BufferedReader buf, int index) throws IOException
	{
		  //VARIAVEIS
		String line="";
      
		//CODIGO
		for (int i = 0; i <= index; i++ )
		{
			line = buf.readLine();
		}
		return line;
	} 

	/**
	 * Metodo que passa uma linha e o numero da linha que será de retorno
	 * @param index Numero da linha desejada
	 * @return A linha especificada
	 * @throws IOException Exceções de entrada e saída
	 */
	public String readLine(int index) throws IOException
	{
		  //VARIAVEIS
		String line = "";

		//CODIGO
		if ((index < 0) || (index >= this.getNumberOfLines()))   // se o índice é menor que 1 e maior que o tamanho do arquivo
		{
			throw (
				new IOException("Número de índice de linha de arquivo inválido. A linha deve ser maior ou igual a 0 e menor do que o número de linhas do arquivo."));
		}

		if (this.existFile())                                    // se o arquivo existe
		{
			FileReader input = new FileReader(this);
			BufferedReader buf = new BufferedReader(input);
         
			line = this.retornaReadLine(buf,index);
		} else                                                   // se o arquivo não existe
			{
			throw (new IOException("Não é possível ler a linha do arquivo \"" + this.getPath() + "\", pois ele não existe."));
		}
		return line;
	}

	/**
	 * Metodo que retorna o valor do pathname com a barra na posição certa 
	 * para o leitura do caminho
	 * @param pathname Caminho do arquivo
	 * @return o pathname com a barra invertida 
	 * @throws IOException Exceção de entrada e saída
	 */
	private String replacePathname(String pathname) throws IOException
	{
		  //VARIAVEIS
		char array_pathname[] = pathname.toCharArray();
		int aux = 0;

		//CODIGO
		for (int i = 0; i < array_pathname.length; i++)
		{
			if (array_pathname[i] == '\\')
			{
				array_pathname[i] = '/';
			}
		}
		String str = new String(array_pathname);
		return str; 
	}
   
	private FileIO copiaArquivoOriginal(String pathname) throws IOException
	{
		String pathnameCopia = pathname + "_";
		this.copyTo(pathnameCopia, true);
		return new FileIO(pathnameCopia);
	}
   
	/**
	 * Metodo que recebe o arquivo original e tenta deletalo se não coseguir 
	 * deleta então a copia
	 * @param fileCopia Copia do arquivo original
	 * @throws IOException Exceções de entrada e saída
	 * @see #moveTo 
	 */
	private void deleteArquivoOriginal (FileIO fileCopia) throws IOException
	{
		  //VARIAVEIS
		  boolean deletou;
   	  
		  //CODIGO
   	   
		deletou = this.delete();                                 // deleta o arquivo original
      
		if (!deletou)                                            // se não consegui deletar o original
		{
			fileCopia.delete(); // deleta a cópia
				throw (new IOException("O arquivo não pôde ser movido. Erro ao excluir o arquivo original"));
		}   	
	}
   
	/**
	 * Metodo que faz uma copia de um arquivo
	 * @param fileCopia Arquivo que será copiado
	 * @param pathname Caminho do arquivo
	 * @param to Arquivo que recebera a copia
	 * @param override 
	 * @throws IOException Exceções de entrada e saída 
	 * @see #moveTo
	 */
	private void fileCopiaArquivo(FileIO fileCopia, String pathname, String to ,boolean override) throws IOException
	{
		//CODIGO 
		try                                                      // realiza a cópia
		{
			fileCopia.copyTo(to, override);
		} catch (IOException e2)                                 // se não conseguiu copiar
		{
			fileCopia.copyTo(pathname, override);                 // restaura o arquivo original
			fileCopia.delete();                                   // deleta a cópia
			  throw (new IOException("O arquivo não pôde ser movido. Erro ao copiar o arquivo"));
		}
		fileCopia.delete();                                      // se tudo foi executado com sucesso, deleta a cópia		
	}
  
	/**
	 * Metodo para copiar um arquivo.
	 * @param to Arquivo atual.
	 * @param override Se arquivo for copiado "true".
	 * @throws IOException Exceção de entrad e saída.
	 * @see #copyTo
	 * @see java.io.File#delete
	 */
	public void moveTo(String to, boolean override) throws IOException
	{
		//VARIAVEIS
		FileIO fileCopia = null;
		String pathname = "";

		//CODIGO
		try
		{
			 pathname = this.getPath();
			 pathname = this.replacePathname(pathname);	      	 
			fileCopia = this.copiaArquivoOriginal(pathname);
		} catch (IOException e)
		{
			throw (new IOException("[FileIO-moveTo] - erro no mover, ao fazer a cópia de segurança: " + e.getMessage()));
		}
		this.deleteArquivoOriginal(fileCopia);                   // deleta o arquivo original   
		this.fileCopiaArquivo(fileCopia,pathname,to,override);   // faz uma copia original

	}

	/**
	 * Metodo que escreve dentro de um Arquivo.
	 * @param text O nome do arquivo que se irá escrever.
	 * @throws IOException Exceção de entrada e saída.
	 */
	public void writeText(String text) throws IOException
	{
		if (this.existFile())
		{
			FileWriter fw = new FileWriter(this);                 // Cria um arquivo no servidor.
			fw.write(text);
			fw.flush();
			fw.close();
		} else
		{
			throw (new IOException("Não é possível escrever dentro do arquivo \"" + this.getPath() + "\", pois ele não existe"));
		}
	}
}