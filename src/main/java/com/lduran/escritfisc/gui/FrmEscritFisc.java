package com.lduran.escritfisc.gui;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.*;

import com.lduran.escritfisc.entity.ObjectBI;
import com.lduran.escritfisc.entity.Organizacao;
import com.lduran.escritfisc.lists.AvailableProcesses;
import com.lduran.escritfisc.service.ApuraPISServImpl;
import com.lduran.escritfisc.service.BuildService;
import com.lduran.escritfisc.util.*;

public class FrmEscritFisc extends JFrame
{
	private JProgressBar progressBar;
	private JButton btnStart;
	private JTextField txtInputFile;
	private JTextField txtOutputFile;
	private JList list;
	private JCheckBox chkAllProcesses;

	public FrmEscritFisc()
	{
		// Create Form Frame
		super("Process Tax Bookkeeping Documents");
		this.setSize(410, 215);
		this.setLocation(500, 280);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		// Label Input File Name
		JLabel lblFileName = this.makeLabel("File Name", 10, 5, 100, 25);
		this.getContentPane().add(lblFileName);

		// Text InputFile
		this.txtInputFile = this.makeTextField("", 10, 28, 185, 25);
		this.getContentPane().add(this.txtInputFile);
		this.txtInputFile.setColumns(10);

		// Button Choose
		JButton btnChoose = this.makeButton("...", 205, 28, 26, 23);
		btnChoose.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser fileopen = new JFileChooser();
				int ret = fileopen.showDialog(null, "Choose file");
				if (ret == JFileChooser.APPROVE_OPTION)
				{
					// Read Text file
					FrmEscritFisc.this.txtInputFile.setText(fileopen.getSelectedFile().toString());
				}
			}
		});
		this.getContentPane().add(btnChoose);

		// If Process All Documents will be processed
		chkAllProcesses = this.makeCheckBox("Process All Documents", 10, 65, 170, 25);
		this.getContentPane().add(chkAllProcesses);

		// ProgressBar
		this.progressBar = this.makeProgressBar(0, 100, 10, 150, 376, 20);
		this.getContentPane().add(this.progressBar);

		// Button Start
		this.btnStart = this.makeButton("Start", 10, 113, 80, 25);
		this.btnStart.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				FrmEscritFisc.this.btnStart.setEnabled(false);
				EventQueue.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						// Use SwingWorker instead of a Simple Thread created via Runnable
						// https://www.devmedia.com.br/trabalhando-com-swingworker-em-java/29331
						new BackgroundWorker().execute();
					}
				});

			}
		});
		this.getContentPane().add(this.btnStart);

		// Button Quit
		JButton quitButton = this.makeButton("Quit", 170, 113, 60, 25);
		quitButton.addActionListener((ActionEvent event) ->
		{
			System.exit(0);
		});
		this.getContentPane().add(quitButton);

		// Label Available Processes
		JLabel lblProcess = this.makeLabel("Available Processes:", 241, 5, 150, 25);
		this.getContentPane().add(lblProcess);

		// String array of Available Processes
		AvailableProcesses[] availableProc = AvailableProcesses.values();

		// ListBox Available Processes
		JScrollPane scroll = this.makeList(availableProc, 241, 30, 145, 115);
		this.getContentPane().add(scroll);

		this.setVisible(true);
	}

	/**
	 * Label Builder
	 *
	 * @param texto
	 * @param posX
	 * @param posY
	 * @param largura
	 * @param altura
	 * @return
	 */
	private JLabel makeLabel(String texto, int posX, int posY, int largura, int altura)
	{
		JLabel lbl = new JLabel(texto);
		lbl.setLocation(posX, posY);
		lbl.setSize(largura, altura);

		return lbl;
	}

	/**
	 * ProgressBar Builder
	 *
	 * @param min
	 * @param max
	 * @param posX
	 * @param posY
	 * @param largura
	 * @param altura
	 * @return
	 */
	private JProgressBar makeProgressBar(int min, int max, int posX, int posY, int largura, int altura)
	{
		JProgressBar pb = new JProgressBar();
		pb.setStringPainted(true);
		pb.setMinimum(min);
		pb.setMaximum(max);
		pb.setBounds(posX, posY, largura, altura);

		return pb;
	}

	/**
	 * TextField Builder
	 *
	 * @param texto
	 * @param posX
	 * @param posY
	 * @param largura
	 * @param altura
	 * @return
	 */
	private JTextField makeTextField(String texto, int posX, int posY, int largura, int altura)
	{
		JTextField txtField = new JTextField(texto);
		txtField.setLocation(posX, posY);
		txtField.setSize(largura, altura);

		return txtField;
	}

	/**
	 * JScrollPane Builder
	 *
	 * @param availableProc
	 * @param posX
	 * @param posY
	 * @param largura
	 * @param altura
	 * @return
	 */
	private JScrollPane makeList(AvailableProcesses[] availableProc, int posX, int posY, int largura, int altura)
	{
		this.list = new JList(availableProc);

		JScrollPane scrollPane = new JScrollPane(this.list);
		scrollPane.setLocation(posX, posY);
		scrollPane.setSize(largura, altura);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		return scrollPane;
	}

	/**
	 * Button Builder
	 *
	 * @param texto
	 * @param posX
	 * @param posY
	 * @param largura
	 * @param altura
	 * @return
	 */
	private JButton makeButton(String texto, int posX, int posY, int largura, int altura)
	{
		JButton btn = new JButton(texto);
		btn.setLocation(posX, posY);
		btn.setSize(largura, altura);

		return btn;
	}

	/**
	 * CheckBox Builder
	 *
	 * @param texto
	 * @param posX
	 * @param posY
	 * @param largura
	 * @param altura
	 * @return
	 */
	private JCheckBox makeCheckBox(String texto, int posX, int posY, int largura, int altura)
	{
		JCheckBox chk = new JCheckBox(texto);
		chk.setLocation(posX, posY);
		chk.setSize(largura, altura);

		return chk;
	}

	/**
	 * Implements SwingWorker
	 * https://www.devmedia.com.br/trabalhando-com-swingworker-em-java/29331
	 *
	 * @author lsdur
	 *
	 */
	public class BackgroundWorker extends SwingWorker<Void, Void>
	{
		public BackgroundWorker()
		{
			this.addPropertyChangeListener(new PropertyChangeListener()
			{
				@Override
				public void propertyChange(PropertyChangeEvent evt)
				{
					FrmEscritFisc.this.progressBar.setValue(BackgroundWorker.this.getProgress());
				}
			});
		}

		@Override
		protected Void doInBackground() throws Exception
		{
			// Read Text File
			Path path = Paths.get(FrmEscritFisc.this.txtInputFile.getText());

			// Get the groups of the listed AvailableProcesses
			List<String> selectedValuesList = new LinkedList<>();
			if (chkAllProcesses.isSelected())
			{
				// Set All Processes to be executed
				String[] array = { "Organizacoes", "Estabelecimentos", "Participantes", "ProdServ", "NotaFiscalDeServico", "ApuracaoPIS" };
				selectedValuesList = Arrays.asList(array);
			}
			else
			{
				// Choose Some Processes
				selectedValuesList = FrmEscritFisc.this.list.getSelectedValuesList();
			}

			// Get the Groups of the Processes
			List<String> grupo = ToolsFactory.getInstance().obtemAvailableProcesses(selectedValuesList);

			// Get the pattern
			Pattern ptn = ToolsFactory.getInstance().makePattern(grupo);

			// Get the file codification
			Charset charset = FileHandler.getInstance().obtemCharSet(FrmEscritFisc.this.txtInputFile.getText());

			// Set the mouse cursor as waiting
			FrmEscritFisc.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

			// Get the number of lines in the file
			int[] qtdLineFile = { 0 };
			try (Stream<String> stream = Files.lines(path, charset).filter(ptn.asPredicate()))
			{
				qtdLineFile[0] = (int) stream.count();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			// Keep the Organization Info
			final Organizacao org = new Organizacao();

			// read file into stream, try-with-resources
			try (Stream<String> stream = Files.lines(path, charset).filter(ptn.asPredicate()))
			{
				// Collectors.toList() converts the stream to List
				List<String> lstProcesses = stream.collect(Collectors.toList());

				JOptionPane.showMessageDialog(null, "File to process: " + qtdLineFile[0] + " lines");
				int[] contador = { 0 };

				for (Object item : selectedValuesList)
				{
					JOptionPane.showMessageDialog(null, "Processing " + item + " block");
					grupo = ToolsFactory.getInstance().obtemAvailableProcesses(item);

					// Get the pattern
					ptn = ToolsFactory.getInstance().makePattern(grupo);
					try (Stream<String> streamProc = lstProcesses.stream().filter(ptn.asPredicate()))
					{
						// Generate List<ObjectBI>
						List<? extends ObjectBI> objectList = new LinkedList<>();
						List<String> auxList = streamProc.collect(Collectors.toList());
						if (!auxList.isEmpty())
						{
							objectList = BuildService.getObjectService(item.toString()).getObjectList(auxList);

							if ((item.toString().equals("Organizacoes")) && (!objectList.isEmpty()))
							{
								org.setCnpj(((Organizacao) objectList.get(0)).getCnpj());
							}

							if ((item.toString().equals("ApuracaoPIS")) && (!org.getCnpj().equals("")) && (!objectList.isEmpty()))
							{
								objectList = objectList.stream().map(apuraPis -> ApuraPISServImpl.setOrganizationInfo(apuraPis, org)).collect(Collectors.toList());
							}

							// Save Report in file
							boolean salvaRelatorio = true;
							if (salvaRelatorio)
							{
								// Get the working path
								String directory = path.getParent().toString();

								// Define the output file
								String outputFile = directory + "\\" + item.toString() + ".csv";

								List<String> report = ListHandler.getInstance().formataRelatorio(objectList, item.toString());
								FileHandler.getInstance().writeStream(outputFile, report, true);
							}

							// Sleep for up to one second.
							try
							{
								Thread.sleep(1000);
							}
							catch (InterruptedException ignore)
							{
							}

							contador[0] = contador[0] + auxList.size();
							this.setProgress(Math.round((contador[0] * 100) / qtdLineFile[0]));
						}

					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			// turn off the wait cursor
			FrmEscritFisc.this.setCursor(null);

			return null;
		}

		@Override
		protected void done()
		{
			JOptionPane.showMessageDialog(null, "Import Data Successfully");
			FrmEscritFisc.this.btnStart.setEnabled(true);
		}
	}
}
