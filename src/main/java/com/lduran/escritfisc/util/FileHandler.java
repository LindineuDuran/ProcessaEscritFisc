package com.lduran.escritfisc.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileHandler
{
	private static FileHandler instance;

	/**
	 * @return the instance
	 */
	public static FileHandler getInstance()
	{
		if (instance == null) {instance = new FileHandler();}

		return instance;
	}

	/**
	 * Obtêm a codificação do arquivo txt
	 * @param inputFile
	 * @return
	 */
	public Charset obtemCharSet(String inputFile)
	{
		Charset charset = StandardCharsets.UTF_8;
		final Path path = Paths.get(inputFile);

		// Receives groups from Organizacoes
		List<String> grupo =  new ArrayList<>();
		grupo.add("|0000|");
		grupo.add("|0005|");

		// Get the pattern
		Pattern ptn = ToolsFactory.getInstance().makePattern(grupo);

		try (Stream<String> stream = Files.lines(path, StandardCharsets.ISO_8859_1).filter(ptn.asPredicate()))
		{
			charset = StandardCharsets.ISO_8859_1;
		}
		catch (Exception e1)
		{
			try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8).filter(ptn.asPredicate()))
			{
				charset = StandardCharsets.UTF_8;
			}
			catch (IOException e2)
			{
				e2.printStackTrace();
			}
		}

		return charset;
	}

	/**
	 * Gravação de arquivo gerado durante processamento por meio de stream
	 * 
	 * @param outputFile
	 * @param dados
	 * @throws IOException
	 */
	public void writeStream(String outputFile, List<String> dados, boolean sobreescreve) throws IOException
	{
		Path path = Paths.get(outputFile);
		Charset utf8 = StandardCharsets.UTF_8;

		if (dados.size() > 0)
		{
			if (sobreescreve)
			{
				try
				{
					Files.write(path, dados);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				try
				{
					if (Files.exists(path))
					{
						dados.remove(0);
					}

					Files.write(path, dados, utf8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
